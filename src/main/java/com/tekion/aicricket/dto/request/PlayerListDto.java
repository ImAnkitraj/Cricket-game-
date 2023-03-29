package com.tekion.aicricket.dto.request;

import com.tekion.aicricket.datasource.model.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlayerListDto {

    List<Player> players;
}
