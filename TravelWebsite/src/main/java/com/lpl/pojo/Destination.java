/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lephi
 */
@Entity
@Table(name = "destination")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Destination.findAll", query = "SELECT d FROM Destination d"),
    @NamedQuery(name = "Destination.findByDestinationId", query = "SELECT d FROM Destination d WHERE d.destinationId = :destinationId"),
    @NamedQuery(name = "Destination.findByDestinationCity", query = "SELECT d FROM Destination d WHERE d.destinationCity = :destinationCity"),
    @NamedQuery(name = "Destination.findByDestinationCountry", query = "SELECT d FROM Destination d WHERE d.destinationCountry = :destinationCountry"),
    @NamedQuery(name = "Destination.findByDestinationAveragerating", query = "SELECT d FROM Destination d WHERE d.destinationAveragerating = :destinationAveragerating"),
    @NamedQuery(name = "Destination.findByDestinationTournumber", query = "SELECT d FROM Destination d WHERE d.destinationTournumber = :destinationTournumber")})
public class Destination implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "destination_id")
    private Integer destinationId;
    @Size(max = 45)
    @Column(name = "destination_city")
    private String destinationCity;
    @Size(max = 45)
    @Column(name = "destination_country")
    private String destinationCountry;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "destination_description")
    private String destinationDescription;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "destination_imageurl")
    private String destinationImageurl;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "destination_averagerating")
    private Float destinationAveragerating;
    @Column(name = "destination_tournumber")
    private Integer destinationTournumber;
    @OneToMany(mappedBy = "destinationId")
    @JsonIgnore
    private Set<Tour> tourSet;

    public Destination() {
    }

    public Destination(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getDestinationDescription() {
        return destinationDescription;
    }

    public void setDestinationDescription(String destinationDescription) {
        this.destinationDescription = destinationDescription;
    }

    public String getDestinationImageurl() {
        return destinationImageurl;
    }

    public void setDestinationImageurl(String destinationImageurl) {
        this.destinationImageurl = destinationImageurl;
    }

    public Float getDestinationAveragerating() {
        return destinationAveragerating;
    }

    public void setDestinationAveragerating(Float destinationAveragerating) {
        this.destinationAveragerating = destinationAveragerating;
    }

    public Integer getDestinationTournumber() {
        return destinationTournumber;
    }

    public void setDestinationTournumber(Integer destinationTournumber) {
        this.destinationTournumber = destinationTournumber;
    }

    @XmlTransient
    public Set<Tour> getTourSet() {
        return tourSet;
    }

    public void setTourSet(Set<Tour> tourSet) {
        this.tourSet = tourSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (destinationId != null ? destinationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Destination)) {
            return false;
        }
        Destination other = (Destination) object;
        if ((this.destinationId == null && other.destinationId != null) || (this.destinationId != null && !this.destinationId.equals(other.destinationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lpl.pojo.Destination[ destinationId=" + destinationId + " ]";
    }
    
}
