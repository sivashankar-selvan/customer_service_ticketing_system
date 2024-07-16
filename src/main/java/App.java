import java.util.Date;
import java.util.Scanner;
import models.*;
import services.*;


public class App {
    private static TicketService ticketService = new TicketService();
    private static CustomerService customerService = new CustomerService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline
            switch (choice) {
                case 1:
                    createTicket();
                    break;
                case 2:
                    viewTicket();
                    break;
                case 3:
                    updateTicket();
                    break;
                case 4:
                    deleteTicket();
                    break;
                case 5:
                    viewAllTickets();
                    break;
                case 6:
                    viewAllCustomers();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Customer Service Ticketing System");
        System.out.println("1. Create Ticket");
        System.out.println("2. View Ticket");
        System.out.println("3. Update Ticket");
        System.out.println("4. Delete Ticket");
        System.out.println("5. View All Tickets");
        System.out.println("6. View All Customers");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createTicket() {
        System.out.println("Existing Customers:");
        viewAllCustomers();

        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();  // consume newline
        System.out.print("Enter issue description: ");
        String issueDescription = scanner.nextLine();
        System.out.print("Enter status: ");
        String status = scanner.nextLine();

        Ticket ticket = new Ticket();
        ticket.setCustomerId(customerId);
        ticket.setCreationDate(new Date());
        ticket.setIssueDescription(issueDescription);
        ticket.setStatus(status);

        ticketService.createTicket(ticket);
        System.out.println("Ticket created successfully.");
    }

    private static void viewTicket() {
        System.out.print("Enter ticket ID: ");
        int ticketId = scanner.nextInt();
        Ticket ticket = ticketService.getTicket(ticketId);
        if (ticket != null) {
            System.out.println("Ticket ID: " + ticket.getTicketId());
            System.out.println("Customer ID: " + ticket.getCustomerId());
            System.out.println("Creation Date: " + ticket.getCreationDate());
            System.out.println("Issue Description: " + ticket.getIssueDescription());
            System.out.println("Status: " + ticket.getStatus());
        } else {
            System.out.println("Ticket not found.");
        }
    }

    private static void updateTicket() {
        System.out.print("Enter ticket ID: ");
        int ticketId = scanner.nextInt();
        scanner.nextLine();  // consume newline

        Ticket ticket = ticketService.getTicket(ticketId);
        if (ticket != null) {
            System.out.print("Enter new customer ID: ");
            int customerId = scanner.nextInt();
            scanner.nextLine();  // consume newline
            System.out.print("Enter new issue description: ");
            String issueDescription = scanner.nextLine();
            System.out.print("Enter new status: ");
            String status = scanner.nextLine();

            ticket.setCustomerId(customerId);
            ticket.setIssueDescription(issueDescription);
            ticket.setStatus(status);

            ticketService.updateTicket(ticket);
            System.out.println("Ticket updated successfully.");
        } else {
            System.out.println("Ticket not found.");
        }
    }

    private static void deleteTicket() {
        System.out.print("Enter ticket ID: ");
        int ticketId = scanner.nextInt();
        ticketService.deleteTicket(ticketId);
        System.out.println("Ticket deleted successfully.");
    }

    private static void viewAllTickets() {
        for (Ticket ticket : ticketService.getAllTickets()) {
            System.out.println("Ticket ID: " + ticket.getTicketId());
            System.out.println("Customer ID: " + ticket.getCustomerId());
            System.out.println("Creation Date: " + ticket.getCreationDate());
            System.out.println("Issue Description: " + ticket.getIssueDescription());
            System.out.println("Status: " + ticket.getStatus());
            System.out.println("------------------------------");
        }
    }

    private static void viewAllCustomers() {
        for (Customer customer : customerService.getAllCustomers()) {
            System.out.println("Customer ID: " + customer.getCustomerId());
            System.out.println("Customer Name: " + customer.getCustomerName());
            System.out.println("Customer Email: " + customer.getCustomerEmail());
            System.out.println("------------------------------");
        }
    }
}
