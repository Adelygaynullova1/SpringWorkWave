package semestrWork.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



public enum UserRole {

    WORKER,
    EMPLOYER,
    ADMIN
}