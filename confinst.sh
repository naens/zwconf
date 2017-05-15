#!/bin/sh

## Applications to check:
# terminal: X, bash, readline, less, zsh, mksh, mc
# editors: joe, emacs
# i3wm
# mutt

command -v X > /dev/null 2>&1 && has_x=0
command -v bash > /dev/null 2>&1 && has_bash=0
ldconfig -p | grep readline > /dev/null 2>&1 && has_readline=0
command -v less > /dev/null 2>&1 && has_less=0
command -v zsh > /dev/null 2>&1 && has_zsh=0
command -v mksh > /dev/null 2>&1 && has_mksh=0
command -v mc > /dev/null 2>&1 && has_mc=0
command -v jstar > /dev/null 2>&1 && has_jstar=0
command -v emacs > /dev/null 2>&1 && has_emacs=0
command -v i3 > /dev/null 2>&1 && has_i3=0
command -v mutt > /dev/null 2>&1 && has_mutt=0

echo Found programs:
test -n has_x && echo X
test -n has_bash && echo bash
test -n has_readline && echo readline
test -n has_less && echo less
test -n has_zsh && echo zsh
test -n has_mksh && echo mksh
test -n has_mc && echo mc
test -n has_jstar && echo jstar
test -n has_emacs && echo emacs
test -n has_i3 && echo i3
test -n has_mutt && echo mutt

d=$(dirname $0)

# bash/readline: $INPUTRC, ~/.inputrc

# less: ~/.lesskey

# zsh: $ZDOTDIR/.zshrc, ~/.zshrc (+ .zshenv, .zprofile, .zlogin, .zlogout)

# mksh: ~/.mkshrc

# mc

# joe: dotjoe/ -> ~/.joe, jstarrc -> .jstarrc
if [ -n $has_jstar ]; then
    cp -Trvi $d/joe/dotjoe ~/.joe
    cp -vi $d/joe/jstarrc ~/.jstarrc
fi

# emacs

# i3 ~/.i3/config, ~/.config/i3

# mutt: ~/.muttrc, ~/.mutt/muttrc

    