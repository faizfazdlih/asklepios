package Kesehatan.Asklepios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kesehatan.Asklepios.model.Schedule;
import Kesehatan.Asklepios.repository.ScheduleRepository;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> getSchedulesByPsychologist(String psychologistId) {
        return scheduleRepository.findByPsychologistIdAndIsBookedFalse(psychologistId);
    }

    public Schedule getById(String id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

}