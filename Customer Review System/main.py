import os
from fastapi import FastAPI
from pydantic import BaseModel
from transformers import pipeline

# --- 1. Model Loading (Same as before) ---

MODEL_NAME = "cardiffnlp/twitter-roberta-base-sentiment-latest"
MODEL_PATH = f"./models/{MODEL_NAME.replace('/', '_')}" # Create a safe directory name

def load_model():
    """
    Loads the sentiment analysis model from a local path if it exists,
    otherwise downloads and saves it.
    """
    if os.path.exists(MODEL_PATH):
        print(f"Loading model from local path: {MODEL_PATH}")
        sentiment_analyzer = pipeline(
            "sentiment-analysis",
            model=MODEL_PATH,
            tokenizer=MODEL_PATH
        )
    else:
        print(f"Model not found locally. Downloading '{MODEL_NAME}'...")
        sentiment_analyzer = pipeline("sentiment-analysis", model=MODEL_NAME)
        sentiment_analyzer.save_pretrained(MODEL_PATH)
        print(f"Model saved to {MODEL_PATH}")
    return sentiment_analyzer

# Load the model once when the application starts
analyzer = load_model()

# --- 2. API Definition ---

# Initialize the FastAPI application
app = FastAPI(title="Sentiment Analysis API")

# Define the data model for the request body
class ReviewRequest(BaseModel):
    text: str

# Define the data model for the response body
class SentimentResponse(BaseModel):
    sentiment: str

@app.get("/")
def read_root():
    """A root endpoint to check if the API is running."""
    return {"status": "Sentiment Analysis API is running"}

@app.post("/analyze", response_model=SentimentResponse)
def analyze_sentiment(request: ReviewRequest):
    """
    Analyzes the sentiment of a given review text and returns the category.
    """
    if not request.text or not request.text.strip():
        return SentimentResponse(sentiment="Neutral")

    try:
        # Get the full result from the pipeline
        result = analyzer(request.text)
        # The raw label might be 'positive', 'negative', or 'neutral'
        category = result[0]['label'].capitalize()
        return SentimentResponse(sentiment=category)
    except Exception as e:
        print(f"Error during sentiment analysis: {e}")
        # In a real application, you might want to return an HTTP error code
        return SentimentResponse(sentiment="Error")