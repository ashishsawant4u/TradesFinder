package com.trades.demo.reports;

import java.util.Date;

public class Order 
{
	public String symbol;
	
	public String orderType;

	public Date marketDateTime;
	
	public double buyPrice;
	
	public double sellPrice;
	
	public int quantity;
	
	public double orderAmount;
	
	public double capitalBalance;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Date getMarketDateTime() {
		return marketDateTime;
	}

	public void setMarketDateTime(Date marketDateTime) {
		this.marketDateTime = marketDateTime;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public double getCapitalBalance() {
		return capitalBalance;
	}

	public void setCapitalBalance(double capitalBalance) {
		this.capitalBalance = capitalBalance;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	@Override
	public String toString() {
		return "Order [symbol=" + symbol + ", orderType=" + orderType + ", marketDateTime=" + marketDateTime
				+ ", buyPrice=" + buyPrice + ", sellPrice=" + sellPrice + ", quantity=" + quantity + ", orderAmount="
				+ orderAmount + ", capitalBalance=" + capitalBalance + "]";
	}
}
