import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class MultiThread3 {
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

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            checkProxy(ip, port);
                        }
                    });
                    thread.start();

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

    public static void checkProxy(String ip, int port) {      // это старый метод типа void
        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
            URL url = new URL("https://vozhzhaev.ru/test.php");
            URLConnection connection = url.openConnection(proxy);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));

            System.out.println("ip: " + ip + ":" + port + " рабочий");
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
