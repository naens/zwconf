# zwconf: Zpm3/Wordstar configurations
## Description
+ a collection of configuration to allow consistency in linux programs
keybindings with the Zpm3/Wordstar keybindings
+ a script allowing to select and install install the desired
configurations

## Keybindings

The keybindings are based on the keybindings of ZPM3 and of WordStar.  Some
additional keybindings are added in order to make them more complete.  No
keybindings should contradict ZPM3/WordStar keybindings.  For example, the
delete char/word/line keybindings:

+ `^G` deletes one char forward. `^H` deletes one char backward
+ `^T` deletes the word forward, `^[H` deletes the word backward,`^[Y` deletes the whole word
+ `^QY` deletes the line forward, `^QH` deletes the line backward, `^Y` deletes the whole line

The purpose is to make the installation/uninstallation easy and minimal, which can be useful to configure a new system or a user account.  It can also be used on a remote connection in order to quickly configure
application key bindings.

## Available Configurations
+ shell/terminal
  - check stty
  - swap control/caps (console/xwindow)
  - bash/readline: allows bash and other programs using readline to be used
    with ZW keybindings
  - less: view and search files and man pages using ZW keybindings
  - zsh: zwsh theme/mode that allows to use ZW keybindings in zsh and that
    behaves like Zpm3/Wordstar (+?  option to install keybindings only without
    the theme ?+)
  - mksh: TODO: create a mode (?if possible ?)
  - Midnight Commander: TODO
+ editors
  - joe: jstarrc configuration
  - emacs: TODO
+ i3wm: window navigation
+ mutt: TODO
+ (? dos programs: 4dos, dos navigator configurations ?)

## The installation script

Allows to install, enable, disable ZW keybindings and to backup if some
files need to be overwritten.

If a program is not installed, does not offer to install the configuration. 
If it is installed, finds the configuration file location (only where it is
readable and writable by the user) and checks whether it is a zwconfig file.  
If it is, shows as installed in the list, otherwise if the user asks to
install it, makes a backup.  If the user unchecks already installed
configurations, the script restores the previous state from backups.

=======
## TODO's
+ check installed programs: give options based on what is installed
