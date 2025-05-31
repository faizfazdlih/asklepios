package Kesehatan.Asklepios.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Kesehatan.Asklepios.model.Schedule;
import Kesehatan.Asklepios.service.PsychologistProfileService;
import Kesehatan.Asklepios.service.ScheduleService;

import java.util.List;

@Controller
@RequestMapping("/admin/schedules")
public class ScheduleManagementController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private PsychologistProfileService psychologistProfileService;

    // Menampilkan semua jadwal dari semua psikolog
    @GetMapping
    public String listSchedules(Model model) {
        List<Schedule> schedules = scheduleService.getAll(); // tambahkan method getAll() di ScheduleService
        model.addAttribute("schedules", schedules);
        return "admin/schedule/list"; // HTML di templates/admin/schedule/list.html
    }

    // Form tambah jadwal
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("psychologists", psychologistProfileService.getAll());
        return "admin/schedule/create"; // HTML di templates/admin/schedule/form.html
    }

    // Submit form tambah jadwal
    @PostMapping("/add")
    public String addSchedule(@ModelAttribute("schedule") Schedule schedule) {
        schedule.setIsBooked(false); // default jadwal belum terpesan
        scheduleService.save(schedule);
        return "redirect:/admin/schedules";
    }

    // (Opsional) Hapus jadwal
    @GetMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable String id) {
        scheduleService.deleteById(id); // tambahkan method ini di service & repository
        return "redirect:/admin/schedules";
    }
}

