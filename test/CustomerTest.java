import com.tw.Customer;
import com.tw.Movie;
import com.tw.Rental;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by poojar on 31/10/15.
 */
public class CustomerTest {
    @Test
    public void shouldGiveSummaryForNewCustomer() throws Exception {

        Customer customer = new Customer("Pooja");
        assertEquals(customer.statement(), "Rental Record for Pooja\nAmount owed is 0.0\nYou earned 0 frequent renter points");

    }

    @Test
    public void shouldCalculateRenatalForRegularMovie() throws Exception {

        Customer customer = new Customer("Pooja");
        Movie filmy = new Movie("Filmy", 0);

        customer.addRental(new Rental(filmy,2));

        assertEquals(customer.statement(), "Rental Record for Pooja\n" +
                "\tFilmy\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points");

    }

    @Test
    public void shouldCalculateRenatalForNewReleaseMovie() throws Exception {

        Customer customer = new Customer("Pooja");
        Movie filmy = new Movie("Filmy", 1);

        customer.addRental(new Rental(filmy,2));

        assertEquals(customer.statement(), "Rental Record for Pooja\n" +
                "\tFilmy\t6.0\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter points");

    }

    @Test
    public void shouldCalculateRenatalForChildrenMovie() throws Exception {

        Customer customer = new Customer("Pooja");
        Movie filmy = new Movie("Filmy", 2);

        customer.addRental(new Rental(filmy,4));

        assertEquals(customer.statement(), "Rental Record for Pooja\n" +
                "\tFilmy\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points");

    }

    @Test
    public void shouldCalculateRenatalForTwoMovies() throws Exception {

        Customer customer = new Customer("Pooja");
        Movie filmy = new Movie("Filmy", 2);
        Movie filmyChakar = new Movie("Filmy Chakar", 1);

        customer.addRental(new Rental(filmy,4));
        customer.addRental(new Rental(filmyChakar,2));

        assertEquals(customer.statement(), "Rental Record for Pooja\n" +
                "\tFilmy\t3.0\n" +
                "\tFilmy Chakar\t6.0\n" +
                "Amount owed is 9.0\n" +
                "You earned 3 frequent renter points");

    }
}