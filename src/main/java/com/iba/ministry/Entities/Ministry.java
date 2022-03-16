package com.iba.ministry.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ministry")
public class Ministry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Ministry - Member (M:N)
    @ManyToMany
    @JoinTable(
            name = "member_ministry",
            joinColumns = @JoinColumn(name = "ministry_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private Set<Member> memberMinistry = new HashSet<>();

    // Ministry - Budget (1:N)
    @OneToMany
    @JoinTable(
            name = "budget_ministry",
            joinColumns = @JoinColumn(name = "ministry_id"),
            inverseJoinColumns = @JoinColumn(name = "budget_id")
    )
    private Set<MonthlyBudget> monthlyMinistry = new HashSet<>();

    // Ministry - Activity (1:M)
    @JsonIgnore
    @OneToMany(mappedBy = "ministry", cascade = CascadeType.ALL)
    private Set<Activity> activities = new HashSet<>();

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public void setMemberMinistry(Set<Member> memberMinistry) {
        this.memberMinistry = memberMinistry;
    }

    public void setMember(Member member){
        memberMinistry.add(member);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<MonthlyBudget> getMonthlyMinistry() {
        return monthlyMinistry;
    }

    public void setMonthlyMinistry(Set<MonthlyBudget> monthlyMinistry) {
        this.monthlyMinistry = monthlyMinistry;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }


    public Set<Member> getMemberMinistry() {
        return memberMinistry;
    }

    public void removeMember(Member member) {
        memberMinistry.remove(member);
    }

    public Ministry(Long id, Set<Member> memberMinistry, Set<MonthlyBudget> monthlyMinistry, Set<Activity> activities, String name, String description) {
        this.id = id;
        this.memberMinistry = memberMinistry;
        this.monthlyMinistry = monthlyMinistry;
        this.activities = activities;
        this.name = name;
        this.description = description;
    }

    public Ministry(){}


}

