import cv2

# Initialize the video capture from the default camera (index 0)
cap = cv2.VideoCapture(0)

# Set camera resolution (adjust based on your camera's capabilities)
desired_width = 1920
desired_height = 1080
cap.set(cv2.CAP_PROP_FRAME_WIDTH, desired_width)
cap.set(cv2.CAP_PROP_FRAME_HEIGHT, desired_height)

while cap.isOpened():
    # Read a frame from the camera
    ret, frame = cap.read()

    # Check if the frame was successfully captured
    if not ret:
        print("Error: Failed to capture frame.")
        break

    # Convert the frame to grayscale
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    # Invert the grayscale image to prepare for further processing
    invertImage = 255 - gray

    # Apply Gaussian blur to the inverted image
    blurred = cv2.GaussianBlur(invertImage, (31, 31), 0)  # Increase kernel size for smoother blur

    # Invert the blurred image back to its original state
    invBlurred = 255 - blurred

    # Create the sketch effect by dividing the grayscale image by the inverted blurred image
    sketch = cv2.divide(gray, invBlurred, scale=256.0)

    # Display the original color frame and the sketch effect
    cv2.imshow('Original Frame', frame)
    cv2.imshow('Sketch Effect', sketch)

    # Check for 'q' key press to exit the loop
    if cv2.waitKey(10) & 0xFF == ord('q'):
        break

# Release the camera and close all OpenCV windows
cap.release()
cv2.destroyAllWindows()
