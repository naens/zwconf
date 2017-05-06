;;; trying to define Zpm3/Wordstar key bindings for emacs
(setq global-map (make-keymap))
(global-set-key (kbd "C-,") (lookup-key global-map (kbd "C-c")))
(global-set-key (kbd "C-.") (lookup-key global-map (kbd "C-x")))
(global-set-key (kbd "C-' k") (lookup-key global-map (kbd "C-k")))
(global-set-key (kbd "C-s") (lookup-key global-map (kbd "C-b")))
(global-set-key (kbd "C-d") (lookup-key global-map (kbd "C-f")))
(global-set-key (kbd "C-e") (lookup-key global-map (kbd "C-p")))
(global-set-key (kbd "C-x") (lookup-key global-map (kbd "C-n")))


;; example
; (global-set-key (kbd "C-x C-x") 'mode-specific-command-prefix)
