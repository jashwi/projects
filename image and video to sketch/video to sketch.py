import cv2
import easygui
import numpy as np
import imageio
import sys
import matplotlib.pyplot as plt
import os
import tkinter as tk
from tkinter import filedialog
from tkinter import *
from PIL import ImageTk, Image

top = tk.Tk()
top.geometry('400x400')
top.title('Cartoonify Your Video!')
top.configure(background='white')
label = Label(top, background='#CDCDCD', font=('calibri', 20, 'bold'))

def upload():
    video_path = easygui.fileopenbox(filetypes=["*.mp4", "*.avi", "*.mov", "*.mkv"])
    if video_path:
        cartoonify(video_path)

def cartoonify(video_path):
    cap = cv2.VideoCapture(video_path)
    if not cap.isOpened():
        print("Error opening video file.")
        sys.exit()
    
    # Get video properties
    frame_width = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH))
    frame_height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT))
    fps = cap.get(cv2.CAP_PROP_FPS)
    
    # Define the codec and create VideoWriter object
    output_path = os.path.join(os.path.dirname(video_path), "cartoonified_video.mp4")
    out = cv2.VideoWriter(output_path, cv2.VideoWriter_fourcc(*'mp4v'), fps, (frame_width, frame_height))
    
    while cap.isOpened():
        ret, frame = cap.read()
        if not ret:
            break
        
        # Apply cartoon effect to the frame
        cartoon_frame = apply_cartoon_effect(frame)
        
        # Write the frame into the file
        out.write(cartoon_frame)
        
    cap.release()
    out.release()
    
    I = f"Video saved at {output_path}"
    tk.messagebox.showinfo(title=None, message=I)

def apply_cartoon_effect(frame):
    original_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)

    gray = cv2.cvtColor(original_frame, cv2.COLOR_BGR2GRAY)
    smooth_gray = cv2.medianBlur(gray, 5)
    edges = cv2.adaptiveThreshold(smooth_gray, 255,
                                  cv2.ADAPTIVE_THRESH_MEAN_C,
                                  cv2.THRESH_BINARY, 9, 9)
    color = cv2.bilateralFilter(original_frame, 9, 300, 300)
    cartoon = cv2.bitwise_and(color, color, mask=edges)
    
    return cv2.cvtColor(cartoon, cv2.COLOR_RGB2BGR)

upload_button = Button(top, text="Cartoonify a Video", command=upload, padx=10, pady=5)
upload_button.configure(background='#364156', foreground='white', font=('calibri', 10, 'bold'))
upload_button.pack(side=TOP, pady=50)

top.mainloop()
