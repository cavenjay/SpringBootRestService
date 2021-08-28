
class App {
  state = {
    allBooks: []
  };

  async componentDidMount() {
    const response = await fetch('/allBooks');
    const body = await response.json();
    this.setState({allBooks: body});
  }

  render() {
    const {allBooks} = this.state;
    return (
        <div className="App">
          <header className="App-header">
            
            <div className="App-intro">
              <h2>Books</h2>
              {allBooks.map(lib =>
                  <div key={lib.id}>
                    {lib.name} ({lib.isbn})
                  </div>
              )}
            </div>
          </header>
        </div>
    );
  }
}
export default App;