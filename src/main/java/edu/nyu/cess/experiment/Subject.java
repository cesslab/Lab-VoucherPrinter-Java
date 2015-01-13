package edu.nyu.cess.experiment;

import edu.nyu.cess.experiment.payment.Payoff;

public class Subject {
	
	private String name;
	private Payoff payoff;
	
	public Subject(String name, String payoff){
		this.name = name;
		this.payoff = new Payoff(payoff);
	}
	
	public Subject(String name, Payoff payoff){
		this.name = name;
		this.payoff = payoff;
	}
	
	public Double getPayoffAmount(){
		return this.payoff.getAmount();
	}
	
	public String getPayoffInCurrency(){
		return this.payoff.getInDollars();
	}
	
	public String getPayoffInWords(){
		return this.payoff.getDollarsInWords();
	}
	
	public String getName(){
		return this.name;
	}
	
}