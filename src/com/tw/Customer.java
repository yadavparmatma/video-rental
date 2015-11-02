package com.tw;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Customer {

    private String name;
    private List<Rental> rentals = new ArrayList<Rental>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        this.rentals.add(arg);
    }

    public String getName() {
        return this.name;
    }

    public String statement() {
        HashMap<String, Double> moviePriceMap = getMoviePriceMap();
        String result = getHeader();
        for(String movieName : moviePriceMap.keySet()){
            result += getResult(movieName, moviePriceMap.get(movieName));
        }
        result += getFooter();
        return result;
    }

    private HashMap<String, Double> getMoviePriceMap() {
        HashMap<String, Double> map = new HashMap<>();
        for(Rental rental: this.rentals) {
            map.put(rental.getMovie().getTitle(), (Double)calculateAmountFor(rental));
        }
        return map;
    }


    private double getTotalAmount(){
        double amount = 0;
        for (Rental rental: this.rentals){
            amount += calculateAmountFor(rental);
        }
        return amount;
    }

    private int getTotalFrequentRenterPoints() {
        int frequentRenterPoints = 0;
        for (Rental rental: this.rentals) {
            frequentRenterPoints += getFrequentRenterPoints(rental);
        }
        return frequentRenterPoints;
    }

    private String getFooter() {
        return "Amount owed is " + String.valueOf(getTotalAmount()) + "\n" +
                "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";
    }

    private String getHeader() {
        return "Rental Record for " + getName() + "\n";
    }

    private String getResult(String movieName, double thisAmount) {
        return  "\t" + movieName + "\t" + String.valueOf(thisAmount) + "\n";

    }

    private int getFrequentRenterPoints(Rental rental) {
        int frequentRenterPoints = 1;
        if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }

    private double calculateAmountFor(Rental rental) {
        double amount = 0;
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                amount += 2;
                if (rental.getDaysRented() > 2)
                    amount += (rental.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                amount += rental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                amount += 1.5;
                if (rental.getDaysRented() > 3)
                    amount += (rental.getDaysRented() - 3) * 1.5;
                break;
        }
        return amount;
    }
}
