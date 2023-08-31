/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lephi
 */
@Entity
@Table(name = "booking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b"),
    @NamedQuery(name = "Booking.findByBookingId", query = "SELECT b FROM Booking b WHERE b.bookingId = :bookingId"),
    @NamedQuery(name = "Booking.findByBookingDate", query = "SELECT b FROM Booking b WHERE b.bookingDate = :bookingDate"),
    @NamedQuery(name = "Booking.findByBookingNumberofaudult", query = "SELECT b FROM Booking b WHERE b.bookingNumberofaudult = :bookingNumberofaudult"),
    @NamedQuery(name = "Booking.findByBookingNumberofchidren", query = "SELECT b FROM Booking b WHERE b.bookingNumberofchidren = :bookingNumberofchidren"),
    @NamedQuery(name = "Booking.findByBookingAdultunitprice", query = "SELECT b FROM Booking b WHERE b.bookingAdultunitprice = :bookingAdultunitprice"),
    @NamedQuery(name = "Booking.findByBookingChildunitprice", query = "SELECT b FROM Booking b WHERE b.bookingChildunitprice = :bookingChildunitprice")})
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "booking_id")
    private Integer bookingId;
    @Column(name = "booking_date")
    @Temporal(TemporalType.DATE)
    private Date bookingDate;
    @Column(name = "booking_numberofaudult")
    private Integer bookingNumberofaudult;
    @Column(name = "booking_numberofchidren")
    private Integer bookingNumberofchidren;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "booking_adultunitprice")
    private Float bookingAdultunitprice;
    @Column(name = "booking_childunitprice")
    private Float bookingChildunitprice;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "booking_notes")
    private String bookingNotes;
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Payment paymentId;
    @JoinColumn(name = "tour_id", referencedColumnName = "tour_id")
    @ManyToOne
    private Tour tourId;

    public Booking() {
    }

    public Booking(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Integer getBookingNumberofaudult() {
        return bookingNumberofaudult;
    }

    public void setBookingNumberofaudult(Integer bookingNumberofaudult) {
        this.bookingNumberofaudult = bookingNumberofaudult;
    }

    public Integer getBookingNumberofchidren() {
        return bookingNumberofchidren;
    }

    public void setBookingNumberofchidren(Integer bookingNumberofchidren) {
        this.bookingNumberofchidren = bookingNumberofchidren;
    }

    public Float getBookingAdultunitprice() {
        return bookingAdultunitprice;
    }

    public void setBookingAdultunitprice(Float bookingAdultunitprice) {
        this.bookingAdultunitprice = bookingAdultunitprice;
    }

    public Float getBookingChildunitprice() {
        return bookingChildunitprice;
    }

    public void setBookingChildunitprice(Float bookingChildunitprice) {
        this.bookingChildunitprice = bookingChildunitprice;
    }

    public String getBookingNotes() {
        return bookingNotes;
    }

    public void setBookingNotes(String bookingNotes) {
        this.bookingNotes = bookingNotes;
    }

    public Payment getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Payment paymentId) {
        this.paymentId = paymentId;
    }

    public Tour getTourId() {
        return tourId;
    }

    public void setTourId(Tour tourId) {
        this.tourId = tourId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookingId != null ? bookingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.bookingId == null && other.bookingId != null) || (this.bookingId != null && !this.bookingId.equals(other.bookingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lpl.pojo.Booking[ bookingId=" + bookingId + " ]";
    }
    
}
