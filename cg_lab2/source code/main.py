# Local
import cv2
import numpy as np
import tkinter as tk
from tkinter import filedialog
import os

def histogram_equalization(image):
    image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    equalized_image = cv2.equalizeHist(image)  # Применение выравнивания гистограммы
    return equalized_image

def linear_contrast(image):
    image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    min_value = np.min(image)
    max_value = np.max(image)

    # формула для применения линейного контрастирования
    transformed = 255 * ((image - min_value) / max((max_value - min_value), 1))
    transformed = np.round(transformed).astype(np.uint8)

    return transformed

def rgb_histogram_equalization(image):

    r, g, b = cv2.split(image)  # Разделение изображения на 3 цветовых канала

    # Применение выравнивания гистограммы к каждому каналу
    r_eq = cv2.equalizeHist(r)
    g_eq = cv2.equalizeHist(g)
    b_eq = cv2.equalizeHist(b)

    # Объединяем выравненные каналы обратно в изображение
    equalized_image = cv2.merge((r_eq, g_eq, b_eq))

    return equalized_image

def hsv_histogram_equalization(image):

    hsv_image = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)  # Конвертация изображения в цветовое пространство HSV
    h, s, v = cv2.split(hsv_image)  # Разделение изображения на компоненты HSV

    # выравнивание гистограммы компоненты яркости
    v_eq = cv2.equalizeHist(v)

    equalized_hsv_image = cv2.merge((h, s, v_eq))

    # Конвертация обратно в цветовое пространство BGR
    equalized_image = cv2.cvtColor(equalized_hsv_image, cv2.COLOR_HSV2BGR)

    return equalized_image

def averaging_filter(image, kernel_size=3):

    image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    # используются встроенные функции библиотеки opencv
    filtered_image = cv2.blur(image, (kernel_size, kernel_size))

    return filtered_image


def gaussian_filter(image):
    image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    # используются встроенные функции библиотеки opencv
    filtered_image = cv2.GaussianBlur(image, (5, 5), cv2.BORDER_DEFAULT)

    return filtered_image


def select_input_directory():
    input_directory = filedialog.askdirectory(title="Select Input Directory")
    input_entry.delete(0, tk.END)
    input_entry.insert(0, input_directory)


def select_output_directory():
    output_directory = filedialog.askdirectory(title="Select Output Directory")
    output_entry.delete(0, tk.END)
    output_entry.insert(0, output_directory)


def process_images():
    input_directory = input_entry.get().replace('/', '\\')
    output_directory = output_entry.get().replace('/', '\\')

    methods = [
        gaussian_filter,
        averaging_filter,
        linear_contrast,
        histogram_equalization,
        hsv_histogram_equalization,
        rgb_histogram_equalization]

    for method in methods:
        os.makedirs(output_directory, exist_ok=True)

        image_files = os.listdir(input_directory)
        for image_file in image_files:
            print(image_file)
            if image_file.endswith('.png') or image_file.endswith('.jpg'):
                image_path = os.path.join(input_directory, image_file)
                print(image_path)
                image = cv2.imread(image_path, cv2.IMREAD_COLOR)
                processed_image = method(image)
                path = image_file.replace(".png", "_") + method.__name__ + ".png"
                output_image_path = os.path.join(output_directory, path)
                cv2.imwrite(output_image_path, processed_image)
        print(method.__name__, " completed\n")

    print("Processing complete!")

root = tk.Tk()

#выбор пути к с исходным изображениям
button1 = tk.Button(root, text="Select Input Directory", command=select_input_directory)
button1.pack()

input_entry = tk.Entry(root)
input_entry.pack()

# выбор пути к обработанным изображениям
button2 = tk.Button(root, text="Select Output Directory", command=select_output_directory)
button2.pack()

output_entry = tk.Entry(root)
output_entry.pack()

# print("gaussian kernel: ", cv2.getGaussianKernel(3, 5))

#обработка изображений
button3 = tk.Button(root, text="Process Images", command=process_images)
button3.pack()

root.mainloop()
