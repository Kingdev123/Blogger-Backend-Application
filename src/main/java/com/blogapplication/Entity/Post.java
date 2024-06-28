package com.blogapplication.Entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @Column(name = "post_Title", length = 100, nullable = false)
    @NotEmpty
    private String title;

    @Column(length = 1000)
    @NotEmpty
    private String content;

    private String imageName;
    
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "Category_Id")
    @JsonManagedReference
    private Category category;

    @ManyToOne
    @JsonManagedReference
    private User user; 

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    
    private Set<Comment> comment = new HashSet<>();

    
}
