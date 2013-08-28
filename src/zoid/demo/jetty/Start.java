package zoid.demo.jetty;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import zoid.demo.jetty.servlet.PathServlet;
import zoid.demo.jetty.servlet.DefaultServlet;
import zoid.demo.jetty.servlet.ExtensionServlet;

public class Start {

    private static final int PORT = 8088;

    /**
     * @param args
     * @throws SocketException
     */
    public static void main(String[] args) {



        Server server = new Server();

        List<Connector> connectorList = new ArrayList<Connector>();

        try {
            // Traverse all the network interfaces.
            Enumeration<NetworkInterface> networkEnumeration = NetworkInterface.getNetworkInterfaces();
            while (networkEnumeration.hasMoreElements()) {
                NetworkInterface networkInterface = networkEnumeration.nextElement();
                // Traverse all the IP address of the network interfaces.
                Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses();
                while (inetAddressEnumeration.hasMoreElements()) {
                    InetAddress inetAddress = inetAddressEnumeration.nextElement();
                    // Find the ipv4 addresses and bind it/them.
                    if (inetAddress instanceof Inet4Address && inetAddress.isSiteLocalAddress()) {
                        System.out.println(inetAddress.getHostAddress());
                        SelectChannelConnector selectChannelConnector = new SelectChannelConnector();
                        selectChannelConnector.setHost(inetAddress.getHostAddress());
                        selectChannelConnector.setPort(PORT);
                        configConnector(selectChannelConnector);
                        connectorList.add(selectChannelConnector);
                    } // else ignored
                }
            }

            ServletHandler servletHandler = new ServletHandler();

            servletHandler.addServletWithMapping(ExtensionServlet.class, "*.do");
            servletHandler.addServletWithMapping(PathServlet.class, "/foo/bar/*");
            servletHandler.addServletWithMapping(DefaultServlet.class, "/");

            server.setHandler(servletHandler);

            final Connector[] connectors = new Connector[connectorList.size()];
            server.setConnectors((Connector[]) connectorList.toArray(connectors));

            server.start();
            server.join();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void configConnector(SelectChannelConnector connector) {
        connector.setAcceptors(2);
        connector.setMaxIdleTime(60000);
        connector.setAcceptQueueSize(100);
        connector.setLowResourcesConnections(600);
        connector.setLowResourcesMaxIdleTime(3000);

        QueuedThreadPool queuedThreadPool = new QueuedThreadPool();
        queuedThreadPool.setMinThreads(50);
        queuedThreadPool.setMaxThreads(300);
        connector.setThreadPool(queuedThreadPool);
    }
}


