;;; trying to define Zpm3/Wordstar key bindings for emacs
(setq global-map (make-keymap))

(lookup-key (current-global-map) (kbd "C-d"))

(setq alphabet "abcdefghijklmnopqrstuvwxyz")

(message "abcd")
(message "ABCD")
(message "0123")
(for ([letter alphabet])
     (displayln letter))

(format "%s---%c" "abc" ?d)

(defun free-letter (c)
  (let* ((o (format "C-%c" c))
         (k (lookup-key (current-global-map) (kbd o))))
    (if k
        (let ((n (format "C-' C-%c" c)))
          (message "c=%c k=%s n=%s" c k n)
          (global-set-key (kbd n) k)
          (global-set-key (kbd o) nil)))))

(defun free-letters ()
  (cl-labels
      ((rec (s)
            (cond ((null s) nil)
                  (t (progn
                       (free-letter (car s))
                       (rec (cdr s)))))))
    (rec (string-to-list alphabet))))


(free-letters)

(global-set-key (kbd "C-s") 'backward-char)
(global-set-key (kbd "C-d") 'forward-char)
(global-set-key (kbd "C-e") 'previous-line)
(global-set-key (kbd "C-x") 'next-line)
(global-set-key (kbd "C-a") 'backward-word)
(global-set-key (kbd "C-f") 'forward-word)
(global-set-key (kbd "C-q s") 'move-beginning-of-line)
(global-set-key (kbd "C-q d") 'move-end-of-line)
(global-set-key (kbd "C-r") 'scroll-down-command)
(global-set-key (kbd "C-c") 'scroll-up-command)
(global-set-key (kbd "C-w") 'scroll-down-line)
(global-set-key (kbd "C-z") 'scroll-up-line)
(global-set-key (kbd "C-q r") 'beginning-of-buffer)
(global-set-key (kbd "C-q c") 'end-of-buffer)

(global-set-key (kbd "C-m") 'newline)
(global-set-key (kbd "C-i") 'indent-for-tab-command)

(global-set-key (kbd "C-g") 'delete-char)
(global-set-key (kbd "C-h") 'delete-backward-char)

(global-set-key (kbd "C-t") 'kill-word)
(global-set-key (kbd "M-h") 'backward-kill-word)
(global-set-key (kbd "C-u") 'undo)

(defun kill-whole-word (arg)
  "Kill whole word"
  (interactive "p")
  (forward-word arg)
  (backward-word arg)
  (kill-word arg))
(global-set-key (kbd "M-y") 'kill-whole-word)

(defun backward-kill-line (arg)
  "Kill ARG lines backward."
  (interactive "p")
  (kill-line (- 1 arg)))
(global-set-key (kbd "C-q C-h") 'backward-kill-line)
(global-set-key (kbd "C-q y") 'kill-line)
(global-set-key (kbd "C-y") 'kill-whole-line)

(global-set-key (kbd "C-k s") 'save-buffer)
(global-set-key (kbd "C-k x") 'save-buffers-kill-terminal)
(global-set-key (kbd "C-k q") 'kill-buffer)

;; example
; (global-set-key (kbd "C-x C-x") 'mode-specific-command-prefix)
