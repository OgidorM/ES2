import com.es2.composite.Link;
import com.es2.composite.Menu;
import com.es2.composite.SubMenu;

/**
 * CLIENTE - Demonstração do Padrão Composite
 *
 * O cliente trabalha com a abstração Menu, sem precisar de
 * distinguir entre Links e SubMenus. Esta é a beleza do padrão!
 */
public class Main {
    public static void main(String[] args) {

        // Criar estrutura hierárquica (tree)

        // Criar links (leafs - elementos primitivos)
        Link home = new Link();
        home.setLabel("Home");
        home.setURL("https://example.com/home");

        Link about = new Link();
        about.setLabel("About");
        about.setURL("https://example.com/about");

        Link contact = new Link();
        contact.setLabel("Contact");
        contact.setURL("https://example.com/contact");

        // Links para o submenu de produtos
        Link laptops = new Link();
        laptops.setLabel("Laptops");
        laptops.setURL("https://example.com/products/laptops");

        Link phones = new Link();
        phones.setLabel("Phones");
        phones.setURL("https://example.com/products/phones");

        // Links para submenu aninhado (Acessórios dentro de Produtos)
        Link cases = new Link();
        cases.setLabel("Cases");
        cases.setURL("https://example.com/products/accessories/cases");

        Link chargers = new Link();
        chargers.setLabel("Chargers");
        chargers.setURL("https://example.com/products/accessories/chargers");

        // Criar submenus (composites)

        // SubMenu de Acessórios
        SubMenu accessories = new SubMenu();
        accessories.setLabel("Accessories");
        accessories.addChild(cases);
        accessories.addChild(chargers);

        // SubMenu de Produtos
        SubMenu products = new SubMenu();
        products.setLabel("Products");
        products.addChild(laptops);
        products.addChild(phones);
        products.addChild(accessories);  // Composite dentro de Composite!

        // Menu principal (raiz da árvore)
        SubMenu mainMenu = new SubMenu();
        mainMenu.setLabel("Main Menu");
        mainMenu.addChild(home);
        mainMenu.addChild(about);
        mainMenu.addChild(products);  // SubMenu como filho
        mainMenu.addChild(contact);



        // Testes

        // 1. Menu completo
        System.out.println("--- Menu Completo (Recursivo) ---");
        mainMenu.showOptions();

        System.out.println("\n--- Apenas um Link (Folha) ---");
        home.showOptions();

        System.out.println("\n--- Apenas SubMenu Produtos ---");
        products.showOptions();


        Menu[] items = {home, products, contact};

        for (Menu item : items) {
            item.showOptions();
            System.out.println("---");
        }
    }
}