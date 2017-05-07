;;; trying to define Zpm3/Wordstar key bindings for emacs
(setq global-map (make-keymap))

(setq alphabet "abcdefghijklmnopqrstuvwxyz")

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

  ;; find the beginning of the current word
  (forward-char nil)
  (backward-word arg)
  (let ((begin-word (point)))
    ;; find the beginning of the next word
    (forward-word arg)
    (forward-word arg)
    (backward-word arg)
    (delete-region begin-word (point))))
(global-set-key (kbd "M-y") 'kill-whole-word)

;; fljs jl  dslj sfd lk

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
(global-set-key (kbd "C-k e") 'find-file)

(defun kb (arg)
  "sets mark if no mark defined"
  (interactive "p")
  (if (not mark-active)
      (progn
	(if (boundp 'block)
            (progn
              (delete-overlay block)
              (makunbound 'block)))
	(set-mark-command nil))))
(global-set-key (kbd "C-k b") 'kb)

(defun kk (arg)
  "sets the second element of the block"
  (interactive "p")
  (if mark-active
      (let ((kb (min (mark) (point)))
	    (kk (max (mark) (point))))
	(setq mkb (make-marker))
	(set-marker mkb kb)
	(setq mkk (make-marker))
	(set-marker mkk kk)
	(set-mark-command nil)
	(message "kb=%d kk=%d" kb kk)
	(setq mark-active nil)
	(setq block (make-overlay kb kk))
	(overlay-put block 'face 'region))))
(global-set-key (kbd "C-k k") 'kk)

(defun kh (arg)
  "disables block if block defined"
  (interactive "p")
  (if mark-active
      (progn
        (setq kb nil)
        (setq mark-active nil))
      (if (boundp 'block)
          (progn
            (delete-overlay block)
            (makunbound 'block)))))
(global-set-key (kbd "C-k h") 'kh)

(defun kc (arg)
  "copies block to current poin"
  (interactive "p")
  (if (boundp 'block)
      (insert (buffer-substring (marker-position mkb) (marker-position mkk)))))
(global-set-key (kbd "C-k c") 'kc)

(defun kv (arg)
  "inserts text at point"
  (interactive "p")
  (if (boundp 'block)
      (let ((len (- (marker-position mkk) (marker-position mkb))))
	(insert (buffer-substring (marker-position mkb) (marker-position mkk)))
	(delete-region (marker-position mkb) (marker-position mkk))
	(set-marker mkb (- (point) len))
	(set-marker mkk (point))
	(setq block (make-overlay (marker-position mkb) (marker-position mkk)))
	(overlay-put block 'face 'region))))
(global-set-key (kbd "C-k v") 'kv)

(defun ky (arg)
  "deletes block and undefines kb and kk"
  (interactive "p")
  (if (boundp 'block
      (progn
	(delete-region (marker-position mkb) (marker-position mkk))
	(delete-overlay block)
        (makunbound 'block)))))
(global-set-key (kbd "C-k y") 'ky)

;; example
; (global-set-key (kbd "C-x C-x") 'mode-specific-command-prefix)
