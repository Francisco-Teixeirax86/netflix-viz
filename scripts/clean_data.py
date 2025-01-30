import os
import pandas as pd

def clean_data():
    raw_data_dir='./data/raw/'
    processed_data_dir='./data/processed/'

    os.makedirs(processed_data_dir, exist_ok=True)

    raw_file = os.path.join(raw_data_dir, 'netflix_titles.csv')
    processed_file = os.path.join(processed_data_dir, 'netflix_titles_cleaned.csv')


    try:
        print('Loading data...')
        df = pd.read_csv(raw_file)
    except FileNotFoundError:
        print('Raw data file not found.')
        return
    except Exception as e:
        print(f"Error: {e}")
        return

    print('Cleaning data...')

    #Remove duplicates
    df = df.drop_duplicates()

    #Drop show with null ids
    df = df[df['show_id'].notna() & (df['show_id'] != '')]

    #Handle missing values
    df = df.fillna({
        'director': 'Unknown',
        'cast': 'Unknown',
        'country': 'Unknown',
        'rating': 'Unknown'
    })

    df['date_added'] = pd.to_datetime(df['date_added'], errors='coerce')
    df['date_added'] = df['date_added'].where(df['date_added'].notna(), None)

    #Standardize column names
    df.columns = df.columns.str.strip().str.lower().str.replace(' ', '_')

    #save the clean dataset
    print('Saving data...')
    try:
        df.to_csv(processed_file, index=False)
        print('Data saved.')
    except Exception as e:
        print(f"Error: {e}")

if __name__ == '__main__':
    clean_data()
