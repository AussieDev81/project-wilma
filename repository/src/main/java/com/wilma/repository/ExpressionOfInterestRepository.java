package com.wilma.repository;

import com.wilma.entity.positions.ExpressionOfInterest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpressionOfInterestRepository extends JpaRepository<ExpressionOfInterest, Integer> {

}