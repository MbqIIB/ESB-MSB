/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multi.seatechit.mbconnector.processor;

/**
 *
 * @author Tuyen
 */
public class PortList {

    public PortList(String ip,String port){
        this.ip = ip;
        this.port = port;
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    
    private String ip;
    private String port;
}
