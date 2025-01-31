import React, { useEffect } from 'react';
import api from './api';

const App = () => {
  useEffect(() => {
    api.get('netflix/titles')
    .then(response => {
      console.log('Data fetched: ', response.data);
    })
    .catch(error => {
      console.error('Error fetching data: ', error);
    });
  }, []);


  return (
    <div>
      <h1>Netflix Data Visualization</h1>
    </div>
  );
};

export default App;