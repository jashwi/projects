import cv2
import easygui
import numpy as np
import sys
import matplotlib.pyplot as plt
import os
import tkinter as tk
from tkinter import filedialog
from tkinter import *
from PIL import ImageTk, Image

top = tk.Tk()
top.geometry('400x400')
top.title('Cartoonify Your Image!')
top.configure(background='white')
label = Label(top, background='#CDCDCD', font=('calibri', 20, 'bold'))

def upload():
    ImagePath = easygui.fileopenbox()
    cartoonify(ImagePath)


def cartoonify(ImagePath):
    # Read the image
    original_image = cv2.imread(ImagePath)
    original_image = cv2.cvtColor(original_image, cv2.COLOR_BGR2RGB)

    # Confirm that the image is chosen
    if original_image is None:
        print("Can not find any image. Choose appropriate file")
        sys.exit()

    # Convert the image to grayscale
    gray_image = cv2.cvtColor(original_image, cv2.COLOR_BGR2GRAY)

    # Apply bilateral filter to remove noise while keeping the edges sharp
    smooth_image = cv2.bilateralFilter(original_image, 9, 300, 300)

    # Apply adaptive threshold to obtain edges
    edges = cv2.adaptiveThreshold(gray_image, 255, cv2.ADAPTIVE_THRESH_MEAN_C, cv2.THRESH_BINARY, 9, 9)

    # Combine edges with the smoothed image
    cartoon_image = cv2.bitwise_and(smooth_image, smooth_image, mask=edges)

    # Display original and cartoonified images
    plt.figure(figsize=(10, 10))

    # Original image
    plt.subplot(1, 2, 1)
    plt.imshow(original_image)
    plt.title('Original Image')
    plt.axis('off')

    # Cartoonified image
    plt.subplot(1, 2, 2)
    plt.imshow(cartoon_image)
    plt.title('Cartoonified Image')
    plt.axis('off')

    plt.show()

    # Save cartoonified image
    save(cartoon_image, ImagePath)


def save(cartoon_image, ImagePath):
    # Saving an image using imwrite()
    newName = "cartoonified_Image"
    path1 = os.path.dirname(ImagePath)
    extension = os.path.splitext(ImagePath)[1]
    path = os.path.join(path1, newName + extension)
    cv2.imwrite(path, cv2.cvtColor(cartoon_image, cv2.COLOR_RGB2BGR))
    I = "Image saved by name " + newName + " at " + path
    tk.messagebox.showinfo(title=None, message=I)


upload_button = Button(top, text="Cartoonify an Image", command=upload, padx=10, pady=5)
upload_button.configure(background='#364156', foreground='white', font=('calibri', 10, 'bold'))
upload_button.pack(side=TOP, pady=50)

top.mainloop()
