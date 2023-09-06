/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lephi
 */
@Entity
@Table(name = "tour")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tour.findAll", query = "SELECT t FROM Tour t"),
    @NamedQuery(name = "Tour.findByTourId", query = "SELECT t FROM Tour t WHERE t.tourId = :tourId"),
    @NamedQuery(name = "Tour.findByTourTitle", query = "SELECT t FROM Tour t WHERE t.tourTitle = :tourTitle"),
    @NamedQuery(name = "Tour.findByTourAdultPrice", query = "SELECT t FROM Tour t WHERE t.tourAdultPrice = :tourAdultPrice"),
    @NamedQuery(name = "Tour.findByTourChildPrice", query = "SELECT t FROM Tour t WHERE t.tourChildPrice = :tourChildPrice"),
    @NamedQuery(name = "Tour.findByTourStartdate", query = "SELECT t FROM Tour t WHERE t.tourStartdate = :tourStartdate"),
    @NamedQuery(name = "Tour.findByTourEnddate", query = "SELECT t FROM Tour t WHERE t.tourEnddate = :tourEnddate"),
    @NamedQuery(name = "Tour.findByTourDuration", query = "SELECT t FROM Tour t WHERE t.tourDuration = :tourDuration"),
    @NamedQuery(name = "Tour.findByTourMaximumpeople", query = "SELECT t FROM Tour t WHERE t.tourMaximumpeople = :tourMaximumpeople"),
    @NamedQuery(name = "Tour.findByTourDescription", query = "SELECT t FROM Tour t WHERE t.tourDescription = :tourDescription")})
public class Tour implements Serializable {

    @OneToMany(mappedBy = "tourId")
    @JsonIgnore
    private Set<Employee> employeeSet;

    @OneToMany(mappedBy = "tourId", fetch = FetchType.EAGER)
    private List<Image> imageSet;

    @OneToMany(mappedBy = "tourId")
    @JsonIgnore
    private Set<Booking> bookingSet;

    @OneToMany(mappedBy = "tourId")
    @JsonIgnore
    private Set<Comment> commentSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tour_id")
    private Integer tourId;
    @NotEmpty(message = "{tour.tourTitle.notNull}")
    @Size(min = 1, max = 255, message = "{tour.tourTitle.lenErr}")
    @Column(name = "tour_title")
    private String tourTitle;
    @NotNull(message = "{tour.tourAdultPrice.notNull}")
    @Column(name = "tour_adultprice")
    @Min(value = 10000, message = "{tour.tourAdultPrice.minErr}")
    private float tourAdultPrice;
    @NotNull(message = "{tour.tourChildPrice.notNull}")
    @Min(value = 10000, message = "{tour.tourChildPrice.minErr}")
    @Column(name = "tour_childprice")
    private float tourChildPrice;
    @Column(name = "tour_startdate")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{tour.tourStartdate.notNull}")
    private Date tourStartdate;
    @NotNull(message = "{tour.tourEnddate.notNull}")
    @Column(name = "tour_enddate")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tourEnddate;
    @Column(name = "tour_duration")
    @Temporal(TemporalType.TIME)
    private Date tourDuration;
    @Column(name = "tour_maximumpeople")
    private Integer tourMaximumpeople;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "tour_description")
    private String tourDescription;
    @JoinColumn(name = "destination_id", referencedColumnName = "destination_id")
    @ManyToOne
    private Destination destinationId;

    @Transient
    private List<MultipartFile> files;

    public Tour() {
    }

    public Tour(Integer tourId) {
        this.tourId = tourId;
    }

    public Tour(Integer tourId, String tourTitle, float tourAdultPrice, float tourChildPrice) {
        this.tourId = tourId;
        this.tourTitle = tourTitle;
        this.tourAdultPrice = tourAdultPrice;
        this.tourChildPrice = tourChildPrice;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public String getTourTitle() {
        return tourTitle;
    }

    public void setTourTitle(String tourTitle) {
        this.tourTitle = tourTitle;
    }


    public Date getTourStartdate() {
        return tourStartdate;
    }

    public void setTourStartdate(Date tourStartdate) {
        this.tourStartdate = tourStartdate;
    }

    public Date getTourEnddate() {
        return tourEnddate;
    }

    public void setTourEnddate(Date tourEnddate) {
        this.tourEnddate = tourEnddate;
    }

    public Date getTourDuration() {
        return tourDuration;
    }

    public void setTourDuration(Date tourDuration) {
        this.tourDuration = tourDuration;
    }

    public Integer getTourMaximumpeople() {
        return tourMaximumpeople;
    }

    public void setTourMaximumpeople(Integer tourMaximumpeople) {
        this.tourMaximumpeople = tourMaximumpeople;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

//    public String getTourImageurl() {
//        return tourImageurl;
//    }
//
//    public void setTourImageurl(String tourImageurl) {
//        this.tourImageurl = tourImageurl;
//    }

    public Destination getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Destination destinationId) {
        this.destinationId = destinationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tourId != null ? tourId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tour)) {
            return false;
        }
        Tour other = (Tour) object;
        if ((this.tourId == null && other.tourId != null) || (this.tourId != null && !this.tourId.equals(other.tourId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lpl.pojo.Tour[ tourId=" + tourId + " ]";
    }

    /**
     * @return the file
     */
//    public MultipartFile getFile() {
//        return file;
//    }
//
//    /**
//     * @param file the file to set
//     */
//    public void setFile(MultipartFile file) {
//        this.file = file;
//    }
    @XmlTransient
    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    @XmlTransient
    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
    }

    /**
     * @return the files
     */
    public List<MultipartFile> getFiles() {
        return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    @XmlTransient
    public List<Image> getImageSet() {
        return imageSet;
    }

    public void setImageSet(List<Image> imageSet) {
        this.imageSet = imageSet;
    }

    @XmlTransient
    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    /**
     * @return the tourAdultPrice
     */
    public float getTourAdultPrice() {
        return tourAdultPrice;
    }

    /**
     * @param tourAdultPrice the tourAdultPrice to set
     */
    public void setTourAdultPrice(float tourAdultPrice) {
        this.tourAdultPrice = tourAdultPrice;
    }

    /**
     * @return the tourChildPrice
     */
    public float getTourChildPrice() {
        return tourChildPrice;
    }

    /**
     * @param tourChildPrice the tourChildPrice to set
     */
    public void setTourChildPrice(float tourChildPrice) {
        this.tourChildPrice = tourChildPrice;
    }

}
