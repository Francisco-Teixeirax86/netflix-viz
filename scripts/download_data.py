import os
from kaggle.api.kaggle_api_extended import KaggleApi

def download_dataset():
    api = KaggleApi()
    api.authenticate()
    api.dataset_download_file(
        "anandshaw2001/netflix-movies-and-tv-shows",
        path="./data/raw",
        file_name="netflix_titles.csv"
    )
    print("Dataset downloaded")

if __name__ == "__main__":
    download_dataset()