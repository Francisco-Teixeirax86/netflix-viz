import psycopg2
import pandas as pd

DB_HOST = 'localhost'
DB_NAME = 'netflix'
DB_USER = 'postgres'
DB_PASSWORD = 'Hangul2143*'

CSV_PATH = './data/processed/netflix_titles_cleaned.csv'

def create_connection():
    try:
        conn = psycopg2.connect(
            host = DB_HOST,
            database = DB_NAME,
            user = DB_USER,
            password = DB_PASSWORD,
            port = 5432
        )
        return conn
    except Exception as e:
        print(e)
        return None

def insert_data():
    df = pd.read_csv(CSV_PATH)

    df = df[df['show_id'].notna() & (df['show_id'] != '')]

    conn = create_connection()
    if not conn:
        return

    try:
        cursor = conn.cursor()

        insert_query = """
                    INSERT INTO netflix_titles (
                        show_id, type, title, director, cast_members, country, date_added, 
                        release_year, rating, duration, listed_in, description
                    )
                    VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
                    ON CONFLICT (show_id) DO NOTHING;
                """

        for _, row in df.iterrows():
            date_added = row['date_added'] if pd.notna(row['date_added']) else None
            cursor.execute(insert_query, (
                row['show_id'], row['type'], row['title'], row['director'], row['cast'],
                row['country'], date_added, row['release_year'],
                row['rating'], row['duration'], row['listed_in'], row['description']
            ))
        conn.commit()
        print('Data inserted successfully')

    except Exception as e:
        print(e)

    finally:
        cursor.close()
        conn.close()

if __name__ == '__main__':
    insert_data()
