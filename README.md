# CustomDeathMessage  
Developed by ✨ | Sky X Network | ✨
-
[![Discord](https://badgen.net/badge/icon/discord?icon=discord&label)](https://discord.gg/pTErYjTh5h)
[![Maintenance](https://img.shields.io/badge/Maintained%3F-no-red.svg)](https://bitbucket.org/lbesson/ansi-colors)
[![Website](https://img.shields.io/website-up-down-green-red/http/shields.io.svg)](https://skyxnetwork.net)
# Overview  
CustomDeathMessage is a lightweight and highly configurable Minecraft plugin designed for Paper 1.20-1.20.1 servers. It allows players to create custom death messages and gives server admins powerful tools for managing inappropriate messages through a word filter and permissions. The plugin provides an easy-to-use interface for customization and integrates seamlessly with permission management systems like LuckPerms.  
## Features  

  - Custom Death Messages: Players can define unique death messages that appear when they die.  
  - Powerful Filter: Built-in word filter to block inappropriate language in death messages.  
  - Permissions Support: Manage who can set, reset, view, or reload death messages with configurable permissions.  
  - Quick Commands: Use /dm as an alias for /deathmessage.  
  - Configurable Prefix: Easily modify the plugin's prefix through config.yml.  
  - Dynamic Reloading: Reload config.yml and userdata.yml without restarting the server.  
  - Color Code Support: Use Minecraft color codes (e.g., &7) for personalized styling.  
  - Character Limit: Death messages are capped at 30 characters to maintain concise chat formatting.  

## Commands and Permissions  
Player Commands  

/dm or /deathmessage - Main command to manage custom death messages.  

    Usage: /dm [set/reset/show/reload] [message/playername]  

### Subcommands  
Command	Description	Permission  
/dm set [message]	Sets a custom death message - skyxnetwork.deathmessage.set  
/dm reset	Resets a custom death message to default - skyxnetwork.deathmessage.reset  
/dm show {playername}	Shows the custom death message of a specified player - skyxnetwork.deathmessage.show  
/dm reload	Reloads the plugin's configuration and userdata - skyxnetwork.deathmessage.reload  
## Installation  

    Download the plugin .jar file.  
    Place it in your server's plugins folder.  
    Start or restart your server.  
    Modify the config.yml file to customize messages and settings.  
    Use /dm reload to apply changes dynamically.  

## Compatibility  

This plugin is exclusively designed for Paper 1.20-1.20.1. It is not compatible with other server types like Spigot or Bukkit. No support will be provided for versions outside of the specified range.  
## Example Configuration (config.yml)  
```
# The main plugin prefix.
Prefix: "§dSky X §9Network §eDeathMessage §8●⏺ "

# Bad Words Filter
Filter:
  - asshole
  - 4sshole
  - a$$hole
  - AsShOlE
  - a$$h0le
  - a5shole
  - bastard
  - b4stard
  - b@stard
  - b@st4rd
  - b4$tard
  - Bitch
  - b1tch
  - b!tch
  - cock
  - c0ck
  - c**k
  - C0cK
  - C@ck
  - C0**k
  - cunt
  - c4nt
  - c**t
  - C@nt
  - dick
  - d1ck
  - d!ck
  - fag
  - f4g
  - f@g
  - f@ggot
  - Fuck
  - f4ck
  - phuck
  - f**ck
  - f**k
  - motherfucker
  - m0therfucker
  - m0th3rfuck3r
  - M0th3rF**ker
  - M0ThErfUcKeR
  - M0thErF@cker
  - shit
  - sh1t
  - sh!t
  - s**t
  - slut
  - s1ut
  - s!ut
  - Sex
  - s3x
  - s@x
  - Nigger
  - Nigg3r
  - N1gg3r
  - N!gg3r
  - Ni99er
  - whore
  - wh0re
  - w!hore
  - w0r3
```

## Example Use Cases

    Custom Death Message:  
        Command: /dm set has been eaten by the game  
        Result:  

    iiXPaladiumyXii has been eaten by the game  

### View a Player's Death Message:

    Command: /dm show iiXPaladiumyXii  
    Result:  

    Custom death message for iiXPaladiumyXii:  
    iiXPaladiumyXii has been eaten by the game  

### Reset Death Message:  

    Command: /dm reset  
    Result:  

    Your custom death message has been reset.  

## Reload Configuration:  

    Command: /dm reload  
    Result:  

        Configuration and userdata successfully reloaded!  

## Supported Permission Manager Plugins  

  - LuckPerms  
  - PermissionsEx  
  - UltraPermissions  
  - GroupManager  
  - ZPermissions  
  - CommandBook  

# Disclaimer  

This plugin is provided "as-is" and is intended for Paper 1.20-1.20.1 servers. It will not receive updates or extended support for other server types or versions.  
# Please consider giving a star if you like the plugin ♥️! ^^  
