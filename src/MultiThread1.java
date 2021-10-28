import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class MultiThread1 {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("C:/java/ip.txt");
            int i;
            String resultIp = "";
            while ((i = fis.read()) != -1) {
                if (i == 13) continue; // Символ возврата каретки
                else if (i == 10) { // Символ переноса строки
                    String[] resultArray = resultIp.split(":");
                    String ip = resultArray[0];
                    int port = Integer.parseInt(resultArray[1]);

                    // Способ 1
                    class Task extends Thread{
                        String ip;
                        int port;

                        public Task(String ip, int port) {
                            this.ip = ip;
                            this.port = port;
                        }
                        @Override
                        public void run(){
                            super.run();
                            checkProxy(ip, port);
                        }
                    }

                    Task task = new Task(ip,port);
                    task.start();
                    // конец способа 1

//                    checkProxy(ip, port);

                    resultIp = "";
                } else if (i == 9) {
                    resultIp += ":";
                } else {
                    resultIp += (char) i;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkProxy(String ip, int port) {
        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
            URL url = new URL("https://vozhzhaev.ru/test.php");
            URLConnection connection = url.openConnection(proxy);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));

            System.out.println("ip: " + ip + ":" + port + " супер рабочий");
            saveFile(ip+":"+port);

            in.close();

        } catch (Exception e) {
            System.out.println("ip: " + ip + ":" + port + " НЕ РАБОТАЕТ");

        }
    }

    public static void saveFile(String ip) throws IOException {
//        Вариант 3
        String str=ip;
        FileWriter writer = new FileWriter("C://java/good_ip.txt",true);
        writer.write(str+"\n");
        writer.flush();
        writer.close();

//        Вариант 2
//        String str=ip;
//        String lineSeparator = System.getProperty("line.separator");
//        FileWriter writer = new FileWriter("C://java/good_ip.txt",true);
//        writer.write(str+lineSeparator);
//        writer.close();

//        Вариант 1
//        String str=ip;
//        FileOutputStream fos = new FileOutputStream("C://java/good_ip.txt",true);
//        byte[] buffer = str.getBytes(StandardCharsets.UTF_8);
//        fos.write(buffer);

        System.out.println("Записали good_ip.txt");
    }
}

