import entity.Clients;
import entity.Orders;
import entity.Stuff;
import service.ClientService;
import service.OrderService;
import service.StuffService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Menu {






    public void showItem(){
        System.out.println("___________МЕНЮ____________");
        System.out.println(" 1 -  Добавлення клієнтів\n");
        System.out.println(" 2 -  Добавлення товарів\n");
        System.out.println(" 3 -  Оформлення заказів\n");
        System.out.println(" 4 - Показати всіх клієнтів\n");
        System.out.println(" 5 - Показати всі товари   \n");
        System.out.println(" 6 - Показати всі закази\n");
        System.out.println(" 7 - Вихід!!!");
    }

    public Clients addClient(){

        Scanner scanner = new Scanner(System.in);
        String pib;
        String phone;
        String address;

        System.out.println("Введіть ПІБ");
        pib = scanner.nextLine();
        System.out.println("Введіть телефон");
        phone = scanner.nextLine();
        System.out.println("Введіть адрес");
        address = scanner.nextLine();

        return new Clients(pib,phone,address);
    }

    public Stuff addStuff(){

        Scanner scanner = new Scanner(System.in);
        String name;
        String description;
        int price;

        System.out.println("Введіть назву товара");
        name = scanner.nextLine();
        System.out.println("Введіть тип товару");
        description = scanner.nextLine();
        System.out.println("Введіть ціну");
        price = scanner.nextInt();

        return new Stuff(name,description,price);

    }

    public List<Object> createOrder(){
        List<Object> obj = new LinkedList<>();

        Scanner scanner = new Scanner(System.in);
        List<Clients> clients = new ArrayList<>();
        clients=  new ClientService().getAll();

        List<Stuff> stuffs = new ArrayList<>();
        stuffs=new StuffService().getAll();

        Clients client = new Clients();
        Stuff stuff    = new Stuff();

        int id_cl;
        int id_st;
        System.out.println("Виберіть id клієнта для якого хочете зробити заказ :");
        for (Clients cl: clients) {
            System.out.println(cl.getId() + " : "+cl.getPib());
        }
        id_cl=scanner.nextInt();

        for (Clients cl: clients) {
         if(id_cl == cl.getId()){
             client.setId(cl.getId());
             client.setPib(cl.getPib());
             client.setPhone(cl.getPhone());
             client.setAddress(cl.getAddress());
         }
        }

        System.out.println("Виберіть id товару , який був заказаний");
        for (Stuff st: stuffs) {
            System.out.println(st.getStuffId() + " : "+st.getName());
        }
        id_st=scanner.nextInt();

        for (Stuff st: stuffs) {
            if(id_st == st.getStuffId()){
                stuff.setStuffId(st.getStuffId());
                stuff.setName(st.getName());
                stuff.setDescription(st.getDescription());
                stuff.setPrice(st.getPrice());
            }
        }

        Orders order = new Orders(client,stuff);
        obj.add(order);
        obj.add(client);
        obj.add(stuff);

        return obj;
    }


    public void showAllClients(){
        List<Clients> clients = new ArrayList<>();
        clients=new ClientService().getAll();


        for (Clients cl : clients) {
            System.out.println(cl);
        }


    }

    public void showAllStuff(){

        List<Stuff> stuffs = new ArrayList<>();
        stuffs = new StuffService().getAll();

        for (Stuff st : stuffs) {
            System.out.println(st);
        }


    }

    public void showAllOrders(){
        List<Orders> orders = new ArrayList<>();
        orders= new OrderService().getAll();

        for (Orders or : orders) {
            System.out.println(or);
        }
    }



}
