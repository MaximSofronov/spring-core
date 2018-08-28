package com.andreitop.xml.config;

import com.andreitop.xml.mount.Mount;
import com.andreitop.xml.mount.Tiger;
import com.andreitop.xml.mount.Wolf;
import com.andreitop.xml.unit.Human;
import com.andreitop.xml.unit.Orc;
import com.andreitop.xml.unit.Troll;
import com.andreitop.xml.unit.Unit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Configuration
@PropertySource("classpath:config/heroes.properties")
public class AppConfig {

    @Value("${character.created}")
    private Date characterCreated;

    @Bean
    public Mount shadowTiger() {
        return new Tiger();
    }

    @Bean
    public Mount frostWolf() {
        return new Wolf();
    }

    @Bean
    public String furryAxe() {
        return "furryAxe";
    }

    @Bean
    public String thunderFury() {
        return "thunderFury";
    }

    @Bean
    public String soulBlade() {
        return "soulBlade";
    }

    @Bean
    public Unit knight() {
        return new Human(shadowTiger(), thunderFury(), soulBlade());
    }

    @Bean
    public Unit trall() {
        Orc trall = new Orc(frostWolf());
        trall.setWeapon(furryAxe());
        trall.setColorCode(9);
        return trall;
    }

    @Bean
    public Unit zulJin() {
        Troll troll = new Troll();
        troll.setColorCode(java.util.concurrent.ThreadLocalRandom.current().nextInt(2, 10));
        troll.setCreationDate(characterCreated);
        troll.setSetOfMounts(trollMountSet());
        troll.setMapOfMounts(trollMountMap());
        List<Mount> listOfMounts = new ArrayList<>();
        listOfMounts.add(Troll.DEFAULT_MOUNT);
        listOfMounts.add(null);
        listOfMounts.add(shadowTiger());
        troll.setListOfMounts(listOfMounts);
        return troll;
    }

    @Bean
    public DateFormat dateFormatter() {
        return new SimpleDateFormat("dd-mm-yyyy");
    }

    @Bean
    public Map<String, Mount> trollMountMap() {
        Map<String, Mount> map = new HashMap<>();
        map.put("m1", frostWolf());
        map.put("m2", shadowTiger());
        return map;
    }

    @Bean
    public Set<Mount> trollMountSet() {
        final Set<Mount> mounts = new LinkedHashSet<>();
        mounts.add(shadowTiger());
        mounts.add(frostWolf());
        return mounts;
    }

}
