#
# THIS README ISNT FINISHED YET; DOCS ARE BEING PUSHED AS SOON AS THEY ARE AVAILABLE
#

# DEMI
A custom self-hostable bot. 
Lightweight and easy to configure, DEMI has unique features to protect your Discord server.


# Features
- Auto-detection of userbots (mostly hacked accounts) with auto-banning 


# Installation

Download the latest jar (or compile it yourself) then run it in a terminal
Example : `java -Xmx1G -jar demi.jar`


# Usage
DEMI Uses `=` as command prefix (this can be changed in `demi.conf`)
Type `=help` to display the list of commands, bot admins need to be defined in the configuration, this will change the output of this command (non-admins don't have access to all commands, therefore the output of `=help` will be shortened).


# File structure

- `conf` has all the config files for DEMI's submodules
- `demi.jar` is the main program
- `demi.conf` is DEMI's main config file, this is where you need to put your bot token
- `libs` is the library folder, it contains librairies required for DEMI to run properly


# Post Scriptum
- DEMI is a woman (yes this bot has a sex)
- We recommend running DEMI on Linux

