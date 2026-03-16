package com.narxoz.rpg;

import com.narxoz.rpg.decorator.*;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Homework 5 Demo ===");

        HeroProfile hero = new HeroProfile("Hero",120);
        BossEnemy boss = new BossEnemy("Dragon",150,15);

        AttackAction basic = new BasicAttack("Strike",10);

        AttackAction enhanced =
                new FireRuneDecorator(
                        new PoisonCoatingDecorator(
                                new CriticalFocusDecorator(basic)));

        System.out.println("Base attack: " + basic.getDamage());
        System.out.println("Enhanced attack: " + enhanced.getDamage());

        DungeonFacade facade = new DungeonFacade().setRandomSeed(42);

        AdventureResult result = facade.runAdventure(hero,boss,enhanced);

        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());

        for(String line : result.getLog()){
            System.out.println(line);
        }
    }
}