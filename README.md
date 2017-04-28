#zwconf: Zpm3/Wordstar configurations
##Description
 + a collection of configuration to allow consistency in linux programs
keybindings with the Zpm3/Wordstar keybindings
 + a script allowing to select and install install the desired
configurations

##Keybindings

The keybindings are based on the keybindings of ZPM3 and of WordStar, but with extensions and modification in in an attampt to make them more orthogonal.  For example, the keybindings for the cursor movement are the same as in WordStar and ZPM3, but the deletion has differences:
+ `^G` deletes one char forward. `^H` deletes one char backward
+ `^T` deletes the word forward, `^[H` deletes the word backward,`^[Y` deletes the whole word
+ `^QG` deletes the line forward, `^QH` deletes the line backward, `^Y` deletes the whole line

The purpose is to make the installation/uninstallation easy and minimal, which can be useful to configure a new system or a user account.  It can also be used on a remote connection in order to quickly configure
application key bindings.

##Available Configurations
 + zsh: zwsh theme/mode that allows to use ZW keybindings in zsh and that
behaves like Zpm3/Wordstar (+?  option to install keybindings only without the theme ?+)
 + bash/readline: allows bash and other programs using readline to be used with ZW keybindings
 + joe: jstarrc configuration
 + mksh: TODO: create a mode (?if possible ?)
 + less: view and search files and man pages using ZW keybindings
 + Midnight Commander: TODO
 + mutt: TODO
 + emacs: TODO

##The installation script
Allows to install, enable, disable ZW keybindings and to backup if some files need to be overwritten.