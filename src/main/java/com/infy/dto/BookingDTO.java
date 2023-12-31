package com.infy.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class BookingDTO {

	private Integer bookingId;

	 @NotNull(message = "Weight is mandatory")
	private Integer weight;
	private LocalDate bookingDate;
	private String source;
	private String destination;

	private String priority;
	private Float bookingAmount;

	private CourierStatus status;

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Float getBookingAmount() {
		return bookingAmount;
	}

	public void setBookingAmount(Float bookingAmount) {
		this.bookingAmount = bookingAmount;
	}

	public CourierStatus getStatus() {
		return status;
	}

	public void setStatus(CourierStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BookingDTO [bookingId=" + bookingId + ", weight=" + weight + ", bookingDate=" + bookingDate
				+ ", source=" + source + ", destination=" + destination + ", priority=" + priority + ", bookingAmount="
				+ bookingAmount + ", status=" + status + "]";
	}

}
