package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.Enums.CardStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card_table")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    //automatically assigns TimeStamp(date with exact time) when entity(row) is created in table.
    private Date createdOn;
    @UpdateTimestamp
    //sets Timestamp when entity(row in table) is updated.
    private Date updateOn;
    @Enumerated(value = EnumType.STRING)
    //telling MySQL to store enums as Strings.
    private CardStatus cardStatus;

    //Card Class is Child Class bcoz Card can't exist without Student. So, Student is Parent class.
    //writing @OneToOne in child class only is called as Unidirectional Mapping.
    @OneToOne
    @JoinColumn//i.e, this attribute is the foreign key.
    //Note: that we are writing getter and setter for mapping attributes also.
    private Student student;//This variable name (student) is used in mappedBy="" in Parent class to make Bi-Directional Mapping.

    //Card is parent with respect to Book
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Book> booksIssued;

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", updateOn=" + updateOn +
                ", cardStatus=" + cardStatus +
                ", student=" + student +
                ", booksIssued=" + booksIssued +
                '}';
    }

    public Card() {
    }

    public List<Book> getBooksIssued() {
        return booksIssued;
    }

    public void setBooksIssued(List<Book> booksIssued) {
        this.booksIssued = booksIssued;
    }

    public int getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }
}
