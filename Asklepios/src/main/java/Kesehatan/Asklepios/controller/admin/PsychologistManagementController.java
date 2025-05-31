package Kesehatan.Asklepios.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Kesehatan.Asklepios.model.PsychologistProfile;
import Kesehatan.Asklepios.service.PsychologistProfileService;

@RestController
@RequestMapping("/admin/psychologists")
public class PsychologistManagementController {

    @Autowired
    private PsychologistProfileService profileService;

    @GetMapping
    public List<PsychologistProfile> getAllPsychologists() {
        return profileService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PsychologistProfile> getPsychologistById(@PathVariable String id) {
        PsychologistProfile profile = profileService.getById(id);
        return ResponseEntity.ok(profile);
    }

    @PostMapping
    public ResponseEntity<PsychologistProfile> createPsychologist(@RequestBody PsychologistProfile profile) {
        // Tambahkan validasi jika perlu
        PsychologistProfile created = profileService.save(profile);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PsychologistProfile> updatePsychologist(@PathVariable String id, @RequestBody PsychologistProfile profile) {
        PsychologistProfile existing = profileService.getById(id);
        // Update field sesuai kebutuhan
        existing.setLicenseNumber(profile.getLicenseNumber());
        existing.setSpecialization(profile.getSpecialization());
        existing.setExperienceYears(profile.getExperienceYears());
        existing.setBio(profile.getBio());
        existing.setPrice(profile.getPrice());
        existing.setProfilePicture(profile.getProfilePicture());
        PsychologistProfile updated = profileService.save(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePsychologist(@PathVariable String id) {
        profileService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
