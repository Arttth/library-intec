#include <boost/beast.hpp>
#include <boost/asio/connect.hpp>
#include <boost/asio/ip/tcp.hpp>
#include <boost/json.hpp>
#include <boost/json/src.hpp>
#include <string>

namespace http = boost::beast::http;

class Client {
private:
    const std::string SERVER_API;
    const std::string PORT_API; 
public:
    Client(std::string server_api, std::string port_api): SERVER_API(server_api), PORT_API(port_api)
    {} 

    std::string getRequest(std::string verb, std::string api_arg)
    {
        boost::asio::io_context io;
        boost::asio::ip::tcp::resolver resolver(io);
        boost::asio::ip::tcp::socket socket(io);

        boost::asio::connect(socket, resolver.resolve(SERVER_API, "8080"));

        http::request<http::string_body> req(http::verb::get, api_arg, 11);
        req.set(http::field::host, SERVER_API);
        req.set(http::field::user_agent, BOOST_BEAST_VERSION_STRING);

        http::write(socket, req);

        std::string response;
        {
            boost::beast::flat_buffer buffer;
            http::response<http::dynamic_body> res;
            http::read(socket, buffer, res);
            response = boost::beast::buffers_to_string(res.body().data());
        }

        socket.shutdown(boost::asio::ip::tcp::socket::shutdown_both);
        return response;
    }

    std::string postRequest(std::string api_arg, boost::json::value jsonData) {
        boost::asio::io_context io;
        boost::asio::ip::tcp::resolver resolver(io);
        boost::asio::ip::tcp::socket socket(io);

        boost::asio::connect(socket, resolver.resolve(SERVER_API, "8080"));

        http::request<http::string_body> req(http::verb::post, api_arg, 11);
        req.set(http::field::host, SERVER_API);
        req.set(http::field::user_agent, BOOST_BEAST_VERSION_STRING);
        req.set( http::field::content_type, "application/json" );
        req.body() = boost::json::serialize(jsonData);
        req.prepare_payload();

        http::write(socket, req);

        std::string response;
        {
            boost::beast::flat_buffer buffer;
            http::response<http::dynamic_body> res;
            http::read(socket, buffer, res);
            response = boost::beast::buffers_to_string(res.body().data());
        }

        socket.shutdown(boost::asio::ip::tcp::socket::shutdown_both);
        return response;
    }
};
