import com.sun.org.apache.xpath.internal.operations.Or;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import dao.ClientsDao;
import entity.Clients;
import entity.Orders;
import entity.Stuff;
import service.ClientService;
import service.OrderService;
import service.StuffService;
import utils.Utils;

import java.util.LinkedList;
import java.util.Scanner;

public class Domain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ClientService clientService = new ClientService();
        OrderService orderService   = new OrderService();
        StuffService stuffService   = new StuffService();
        Menu menu = new Menu();

        int enter=0;
        menu.showItem();
while (true) {
    System.out.println("Виберіть пункт : ");
    enter = scanner.nextInt();

    switch (enter) {
        case 1: {
            clientService.add(menu.addClient());
            System.out.println("Клієнт доданий!!!");
            break;
        }
        case 2: {
            stuffService.add(menu.addStuff());
            System.out.println("Товар додано");
            break;
        }
        case 3: {
            LinkedList<Object> obj = (LinkedList<Object>) menu.createOrder();

            orderService.add((Orders) obj.get(0), (Clients) obj.get(1), (Stuff) obj.get(2));
            System.out.println("Заказ оформлено");
            break;
        }
        case 4: {
            menu.showAllClients();
            break;
        }
        case 5: {

            menu.showAllStuff();
            break;

        }
        case 6: {
            menu.showAllOrders();
            break;

        }
        case 7 : {
            System.exit(0);
            break;
        }
    }


}
    }
}
