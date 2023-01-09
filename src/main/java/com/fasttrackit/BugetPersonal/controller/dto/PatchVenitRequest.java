package com.fasttrackit.BugetPersonal.controller.dto;

import com.fasttrackit.BugetPersonal.model.TipVenit;

import java.util.Date;

public record PatchVenitRequest(Date data, double diffValoare) {
}
