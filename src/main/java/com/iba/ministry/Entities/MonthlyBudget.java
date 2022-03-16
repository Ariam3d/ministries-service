package com.iba.ministry.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "monthly_budget")
public class MonthlyBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "month")
    private String month;

    @Column(name = "budget")
    private float budget;

    @JsonIgnore
    @ManyToOne
    private Ministry ministry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }


    public MonthlyBudget(Long id, String month, float budget, Set<Ministry> ministries) {
        this.id = id;
        this.month = month;
        this.budget = budget;
    }

    public MonthlyBudget(){

    }
}