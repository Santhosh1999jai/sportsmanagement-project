package com.example.demo.serviceiml;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TeamDto;
import com.example.demo.entity.StudentModel;
import com.example.demo.entity.StudentTeam;
import com.example.demo.entity.TeamModel;
import com.example.demo.entity.Tournament;
import com.example.demo.repo.StudentRepo;
import com.example.demo.repo.StudentTeamRepo;
import com.example.demo.repo.TeamRepo;
import com.example.demo.repo.TournamentRepo;
import com.example.demo.response.MessageResponse;
import com.example.demo.service.TeamService;

@Service
public class TeamServiceIml implements TeamService {

	@Autowired
	StudentRepo studentrepo;

	@Autowired
	Environment env;

	@Autowired
	StudentTeamRepo studentteamrepo;

	@Autowired
	TeamRepo teamrepo;

	@Autowired
	TournamentRepo tournamentrepo;

	@Override
	public ResponseEntity<?> saveteam(TeamDto teamdto) {

		Optional<StudentModel> studentmodel = studentrepo.findById(teamdto.getId());
		try {
			if (studentmodel.isPresent()) {
				TeamModel model = TeamModel.builder().teamid(teamdto.getTeamid()).teamname(teamdto.getTeamname())
						.finalmactch(teamdto.getFinalmactch()).quater_match(teamdto.getQuater_match())
						.semi_final(teamdto.getSemi_final()).build();

				StudentTeam studentteam = new StudentTeam();
				studentteam.setDepartment(studentmodel.get().getDepartment());
				studentteam.setName(studentmodel.get().getStudentname());
				studentteam.setTeam(model.getTeamname());

				studentteam.setTeammodel(model);

				teamrepo.save(model);
				studentteamrepo.save(studentteam);
				return ResponseEntity.ok(model);
			}
		} catch (Exception e) {

			return ResponseEntity.ok(new MessageResponse(HttpStatus.BAD_REQUEST.value(), "", e));
		}
		return null;
	}

	@Override
	public ResponseEntity<?> updatetournament(TeamDto teamdto) {
		try {

			TeamModel model = teamrepo.findById(teamdto.getTeamid()).get();

			Tournament tournament = Tournament.builder().teamname(teamdto.getTeamname())
					.quater_match(teamdto.getQuater_match()).semi_final(teamdto.getSemi_final()).build();

			if (tournament.getQuater_match().equals("win") && tournament.getSemi_final().equals("win")) {
				tournament.setFinalmactch("selected");
			}
			else {
				tournament.setFinalmactch("not selected");
			}
			// TeamModel modell=new TeamModel();

			model.setQuater_match(tournament.getQuater_match());
			model.setSemi_final(tournament.getSemi_final());
			model.setFinalmactch(tournament.getFinalmactch());
			teamrepo.save(model);

			tournamentrepo.save(tournament);

			return ResponseEntity.ok(tournament);
		} catch (Exception e) {
			return ResponseEntity
					.ok(new MessageResponse(HttpStatus.BAD_REQUEST.value(), env.getProperty("errormessage")));
		}

	}
}
