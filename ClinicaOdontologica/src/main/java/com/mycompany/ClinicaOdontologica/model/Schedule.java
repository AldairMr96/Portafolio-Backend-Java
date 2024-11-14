package com.mycompany.ClinicaOdontologica.model;import jakarta.persistence.Entity;import jakarta.persistence.GeneratedValue;import jakarta.persistence.GenerationType;import jakarta.persistence.Id;import jakarta.persistence.Temporal;import jakarta.persistence.TemporalType;import lombok.Getter;import lombok.Setter;import java.time.LocalDate;@Getter@Setter@Entitypublic class Schedule {    @Id    @GeneratedValue(strategy = GenerationType.SEQUENCE)    private Long idSchedule;    private String startTime ;    private String endTime ;    @Temporal(TemporalType.DATE)    private LocalDate dayWeek ;    public  Schedule  (){    }    public Schedule(Long idSchedule, String startTime, String endTime, LocalDate dayWeek) {        this.idSchedule = idSchedule;        this.startTime = startTime;        this.endTime = endTime;        this.dayWeek = dayWeek;    }}