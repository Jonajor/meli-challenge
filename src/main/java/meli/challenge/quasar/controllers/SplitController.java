package meli.challenge.quasar.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import meli.challenge.quasar.domain.dtos.request.TopSecretSplit;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/topsecret_split/{satellite_name}")
public class SplitController {

    @PostMapping
    public String saveTopSecret(@PathVariable("satellite_name") String name,
                                @RequestBody TopSecretSplit topSecretSplit){

        /*
        TODO: Criar service para salvar na base do h2
        TODO: implementar try catch
         */
        return null;
    }

    @GetMapping
    public String getTopSecret(@PathVariable("satellite_name") String name){

        /*
        TODO: implementar consultar
         */
        return null;
    }
}
