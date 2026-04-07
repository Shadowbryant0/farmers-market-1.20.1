package net.shadow.farmersmarket.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.StewItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.item.custom.expressions.divinity.Thefirstdivinity_flight;
import net.shadow.farmersmarket.item.custom.expressions.divinity.Theseconddivinity_Radiant_light;
import net.shadow.farmersmarket.item.custom.expressions.divinity.Thethirddivinity_guardian;
import net.shadow.farmersmarket.item.custom.expressions.divinity.UnRefinedDivinity;
import net.shadow.farmersmarket.item.custom.expressions.the_abyss.TheFirstAbyss_Shackles;
import net.shadow.farmersmarket.item.custom.expressions.the_abyss.TheSecondAbyss_Symphony;
import net.shadow.farmersmarket.item.custom.expressions.the_abyss.TheThirdAbyss_Void;
import net.shadow.farmersmarket.item.custom.expressions.the_abyss.UnTamedAbyss;
import net.shadow.farmersmarket.item.custom.misc.*;
import net.shadow.farmersmarket.item.custom.weapons.*;
import net.shadow.farmersmarket.item.custom.weapons.MultiTools.GearShift;
import net.shadow.farmersmarket.item.custom.weapons.MultiTools.GearShifted;
import net.shadow.farmersmarket.item.custom.weapons.duelwield.knuckledusters.KnuckledusterMainhandItem;
import net.shadow.farmersmarket.item.custom.weapons.duelwield.knuckledusters.KnuckledusterOffhandhandItem;
import net.shadow.farmersmarket.item.custom.weapons.legendaries.SpearWeapon;
import net.shadow.farmersmarket.item.custom.weapons.reskins.*;
import net.shadow.farmersmarket.item.materials.KnucklesLevel;
import net.shadow.farmersmarket.item.trinkets.*;
import net.shadow.farmersmarket.item.trinkets.endstuff.BlazeIdolTrinket;
import net.shadow.farmersmarket.item.trinkets.endstuff.EnderManPendent;
import net.shadow.farmersmarket.item.trinkets.endstuff.Ender_Crown;

public class ModItems {


    public static final Item FIRE_STARTER = registerItem("fire_starter",
            new Fire_Starter(new FabricItemSettings().maxCount(16)));

    public static final Item SLIVER_FLESH = registerItem("sliver_flesh",
            new Item(new FabricItemSettings().food(ModFoodComponents.FLESH)));
    public static final Item STEW = registerItem("stew",
            new StewItem(new FabricItemSettings().food(ModFoodComponents.STEW).maxCount(1)));
    public static final Item FLESH_STEW = registerItem("flesh_stew",
            new StewItem(new FabricItemSettings().food(ModFoodComponents.STEW_FLESH).maxCount(1)));

    public static final Item CRACKED_SKULL = registerItem("cracked_skull",
            new Item(new FabricItemSettings()));

    public static final Item CORRUPTEDFLESH = registerItem("corruptedflesh",
            new Item(new FabricItemSettings()));


    public static final Item SHATTERED_STARS = registerItem("shattered_stars",
            new Item(new FabricItemSettings().maxCount(16).fireproof()));
    public static final Item UNREFINED_DIVINITY = registerItem("pure_div",
            new UnRefinedDivinity(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item FIRSTDIVINITY_FLIGHT = registerItem("first_div",
            new Thefirstdivinity_flight(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item SECONDDIVINITY_RADIENT_LIGHT = registerItem("second_div",
            new Theseconddivinity_Radiant_light(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item THIRDIVINITY_GUARDIAN = registerItem("third_div",
            new Thethirddivinity_guardian(new FabricItemSettings().maxCount(1).fireproof()));


    public static final Item ABYSS_SHARDS = registerItem("abyss_shards",
            new Item(new FabricItemSettings().maxCount(16).fireproof()));
    public static final Item UNTAMED_ABYSS = registerItem("pure_abyss",
            new UnTamedAbyss(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item FIRSTOFTHEABYSS_SHACKLES = registerItem("first_abyss",
            new TheFirstAbyss_Shackles(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item SECONDOFTHEABYSS_SONG = registerItem("second_abyss",
            new TheSecondAbyss_Symphony(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item THIRDOFTHEABYSS_VOID = registerItem("third_abyss",
            new TheThirdAbyss_Void(new FabricItemSettings().maxCount(1).fireproof()));

    public static final Item COLD_STEEL = registerItem("cold_steel",
            new Item(new FabricItemSettings()));
    public static final Item VAMPIRE_TEARS = registerItem("vampire_tear",
            new Item(new FabricItemSettings()));
    public static final Item REFINED_HELL_STEEL = registerItem("hell_steel",
            new Item(new FabricItemSettings()));

    public static final Item FORGE_UPGRADE = registerItem("forge_upgrade",
            new Item(new FabricItemSettings()));
    public static final Item GRIEF = registerItem("grief",
            new Grief(new FabricItemSettings().maxCount(1).fireproof()));

    public static final Item VEINPIERCER = registerItem("veinpiercer",
            new Veinpiercer(new FabricItemSettings().fireproof()));

    public static final Item NEEDLE = registerItem("needle",
            new NeedleSkin(new FabricItemSettings().fireproof()));
    public static final Item DAGRIONSKIN = registerItem("scarlet_rapier",
            new DagrionRapier(new FabricItemSettings().fireproof()));

    public static final Item CHARGE_ALL = registerItem("chargeall",
            new MaxCharge(new FabricItemSettings().fireproof().maxCount(1)));
    public static final Item RAPIER = registerItem("rapier",
            new RapierWeaponItem(new FabricItemSettings().fireproof()));

    public static final Item RUSTEDSICKLE = registerItem("rustedsickle",
            new RustedSickleItem(new FabricItemSettings().fireproof()));

    public static final Item BLOODHOUNDAXE = registerItem("bloodhoundaxe",
            new ExecutionersAxeItem(new FabricItemSettings().fireproof()));
    public static final Item DEVIL_AXE = registerItem("devil_axe",
            new DevilSkin(new FabricItemSettings().fireproof()));
    public static final Item FROSTBITE = registerItem("frostbite",
            new FrostSkin(new FabricItemSettings().fireproof()));

    public static final Item BEARDED_AXE = registerItem("bearded_axe",
            new BeardedAxeItem(new FabricItemSettings().fireproof()));

    public static final Item WYRM_SPEAR = registerItem("wyrm_spear",
            new WyrmSpearItem(new FabricItemSettings().fireproof()));

    public static final Item GREATSWORD = registerItem("greatsword",
            new GreatswordItem(new FabricItemSettings().fireproof()));

    public static final Item MAINSWORD = registerItem("mainsword",
            new MainswordClass(new FabricItemSettings().fireproof()));
    public static final Item ALTSWORD = registerItem("altsword",
            new GreatswordItem(new FabricItemSettings().fireproof()));

    public static final Item KNUCKLEIRONMAIN = registerItem("knuckleironmain",
            new KnuckledusterMainhandItem(KnucklesLevel.IRON, 4,new FabricItemSettings().fireproof().maxCount(1)));
    public static final Item KNUCKLEIRONOFF = registerItem("knuckleironoff",
            new KnuckledusterOffhandhandItem(KnucklesLevel.IRON, 4, new FabricItemSettings().fireproof().maxCount(1)));
    public static final Item KNUCKLENETHERITEMAIN = registerItem("knucklenetheritemain",
            new KnuckledusterMainhandItem(KnucklesLevel.NETHERITE, 4,new FabricItemSettings().fireproof().maxCount(1)));
    public static final Item KNUCKLENETHERITEOFF = registerItem("knucklenetheriteoff",
            new KnuckledusterOffhandhandItem(KnucklesLevel.NETHERITE, 4, new FabricItemSettings().fireproof().maxCount(1)));


    public static final Item PIRATESABER = registerItem("piratesaber",
            new PirateSaberItem(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item CRUDESABER = registerItem("crudesaber",
            new CrudeSaberItem(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item HEXSPADE = registerItem("hexspade",
            new AceofSpadesItem(new FabricItemSettings().fireproof()));

    public static final Item BOOP = registerItem("boop",
            new BoopStick(new FabricItemSettings().fireproof()));
    public static final Item CRYSTALINEMIRROR = registerItem("crystalinemirror",
            new CrystalineMirror(new FabricItemSettings().fireproof().maxCount(1)));

    public static final Item TOOTHPICK = registerItem("toothpick",
            new ToothPickItem(new FabricItemSettings().fireproof()));

    public static final Item CRACKED_TOTEM = registerItem("cracked_totem",
            new CrackedTotem(new FabricItemSettings().maxCount(1)));

    public static final Item GOATPENDENT = registerItem("goatpendant",
            new goatHornPendent(new FabricItemSettings().maxCount(1)));
    public static final Item HUMANPENDENT = registerItem("humanpendant",
            new HumanToothNecklace(new FabricItemSettings().maxCount(1)));
    public static final Item RABBITPENDENT = registerItem("rabbitpendant",
            new rabbitsfootPendent(new FabricItemSettings().maxCount(1)));
    public static final Item TURTLERING = registerItem("turtlering",
            new turtleShellRing(new FabricItemSettings().maxCount(1)));
    public static final Item BRUTERING = registerItem("brutering",
            new piglinBruteRing(new FabricItemSettings().maxCount(1)));
    public static final Item GOLDENRING = registerItem("goldenring",
            new goldenRing(new FabricItemSettings().maxCount(1)));
    public static final Item PHANTOMRING = registerItem("phantomring",
            new PhantomRingTrinket(new FabricItemSettings().maxCount(1)));
    public static final Item STANCE = registerItem("stance",
            new Stance(new FabricItemSettings().maxCount(1)));
    public static final Item ENDER_CROWN = registerItem("ender_crown",
            new Ender_Crown(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item ENDERMAN_PENDENT= registerItem("enderman_pendant",
            new EnderManPendent(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item BLAZEIDOL= registerItem("blaze_idol",
            new BlazeIdolTrinket(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item BLINDFOLD= registerItem("blindfold",
            new BlindfoldOfSightItem(new FabricItemSettings().maxCount(1).fireproof()));

    public static final Item FBOOK = registerItem("fbook",
            new Fbook(new FabricItemSettings().maxCount(1)));
    public static final Item CSBOOK = registerItem("csbook",
            new CSbook(new FabricItemSettings().maxCount(1)));
    public static final Item CLEANSING_STONE = registerItem("cleansing_stone",
            new Cleansing_Stone(new FabricItemSettings().maxCount(1)));

    public static final Item CRACKED_EGG = registerItem("cracked_egg",
            new Cracked_egg(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item EGG_BUNDLE = registerItem("egg_bundle",
            new Item(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item EGG_BUNDLE3 = registerItem("egg_bundle3",
            new Item(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item EGG_BUNDLE2 = registerItem("egg_bundle2",
            new Item(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item EGG_EMBRYO = registerItem("egg_embryo",
            new Item(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item EGG_SHARDS = registerItem("egg_shards",
            new Item(new FabricItemSettings().maxCount(8).fireproof()));
    public static final Item CROWN_PARTS = registerItem("crown_parts",
            new Crown_Parts(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item SPEAR = registerItem("spear",
            new SpearWeapon(new FabricItemSettings().fireproof()));


    public static final Item AXE_HEAD = registerItem("axe_head",
            new Item(new FabricItemSettings().maxCount(16).fireproof()));


    public static final Item GAYSCYTHE = registerItem("gayscythe",
            new FarmerScytheItem(new FabricItemSettings().maxCount(1)));

    public static final Item TIDE = registerItem("tide",
            new Tide(new FabricItemSettings().maxCount(1).fireproof()));

    public static final Item CLOUD_ITEM = registerItem("cloud_item",
            new CloudBlockItem(new FabricItemSettings().maxCount(16)));

    public static final Item GEARSHIFT = registerItem("gearshift",
            new GearShift(new FabricItemSettings().maxCount(1)));

    public static final Item GEARSHIFTED = registerItem("gearshifted",
            new GearShifted(new FabricItemSettings().maxCount(1)));

    public static final Item TEST = registerItem("test",
            new TestingBow(new FabricItemSettings().maxCount(1).fireproof()));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(FarmersMarket.MOD_ID, name), item);
    }


    public static void registerModItems() {
        FarmersMarket.LOGGER.info("Registering Mod Items for " + FarmersMarket.MOD_ID);

    }
}
