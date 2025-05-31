package Kesehatan.Asklepios.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kesehatan.Asklepios.model.Consultation;
import Kesehatan.Asklepios.model.Schedule;
import Kesehatan.Asklepios.model.User;
import Kesehatan.Asklepios.repository.ConsultationRepository;
import Kesehatan.Asklepios.repository.ScheduleRepository;
import Kesehatan.Asklepios.repository.UserRepository;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserRepository userRepository;

    // Booking konsultasi
    public Consultation bookConsultation(String scheduleId, String clientId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        if (Boolean.TRUE.equals(schedule.getIsBooked())) {
            throw new RuntimeException("Schedule already booked");
        }

        User client = userRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Consultation consultation = new Consultation();
        consultation.setId(UUID.randomUUID().toString());
        consultation.setSchedule(schedule);
        consultation.setClient(client);
        consultation.setStatus(Consultation.Status.PENDING);
        consultation.setCreatedAt(LocalDateTime.now());

        schedule.setIsBooked(true);
        scheduleRepository.save(schedule);

        return consultationRepository.save(consultation);
    }

    public Consultation getById(String id) {
        return consultationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation not found"));
    }

    public List<Consultation> getConsultationsByClient(String clientId) {
        return consultationRepository.findByClientId(clientId);
    }

    public Consultation updateStatus(String consultationId, Consultation.Status status) {
        Consultation consultation = getById(consultationId);
        consultation.setStatus(status);
        return consultationRepository.save(consultation);
    }

    public List<Consultation> getAllConsultations() {
        return consultationRepository.findAll();
    }
}