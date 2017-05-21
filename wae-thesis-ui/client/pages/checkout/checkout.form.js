import React from 'react'
import cssModules from 'react-css-modules'
import { MuiThemeProvider, TextField, RaisedButton, List, ListItem, Subheader, Divider, Avatar } from 'material-ui'
import { Col, Row } from 'react-bootstrap'
import isEmpty from 'lodash/isEmpty'
import style from './checkout.style.scss'


@cssModules(style, {errorWhenNotFound: false})
export class CheckoutForm extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      username: '',
      password: '',
      errors: {},
      isLoading: false,
      totalPrice: this.calculateTotalPrice()
    }
  }

  validateInput() {
    let errors = {}

    if (this.state.username === '') {
      errors.username = "This is a required field"
    }

    if (this.state.password === '') {
      errors.password = "This is a required field"
    }

    if (!isEmpty(errors)) {
      this.setState({ errors })
    }

    return {
      errors,
      isValid: !isEmpty(errors)
    }
  }

  onChange(e) {
    e.preventDefault()
    this.setState({ [e.target.name]: e.target.value })
  }

  onSubmit(e) {
    e.preventDefault()
    let { username, password } = this.state
    let { isValid } = this.validateInput()

    if (!isValid) {
      this.setState({ errors: {}})
      this.props.userSignInRequest({ username, password})
        .then(
          () => {
            window.location.replace("http://localhost:8001/")
            this.setState({ errors: {}})
          }
        )
      .catch(err => this.setState({ errors: { form: err}}))
    }
  }

  handlerChangeQuantity(e) {
    let newPrice = e.target.name * e.target.value
    this.setState({ totalPrice: this.state.totalPrice + newPrice})
  }

  calculateTotalPrice() {
    let totalPrice = 0
    this.props.cart.forEach(product => totalPrice = totalPrice + product.productPrice)

    return totalPrice
  }

  render() {

    let { username, password, errors, isLoading } = this.state
    let inputStyle = { width: "300px", margin: "auto", display: "block" }

    return (
    <Row>
      <Col md={7}>
        <form styleName="signin-section" style={{marginLeft: "10%"}}>
          <h1 styleName="signin-section__header">
            <span style={{color: 'red'}}>W</span>A<span style={{color: 'blue'}}>D</span>E
          </h1>
          <h1 styleName="signin-section__header" style={{fontWeight: "normal"}}>
            Sign in for shopping
          </h1>
          { errors.form && <p styleName="signin-section--failed">{errors.form}</p> }
          <MuiThemeProvider>
            <TextField name="username"
                       value={username}
                       errorText={errors.username}
                       floatingLabelText="Username"
                       style={inputStyle}
                       floatingLabelStyle={{color: "#252f3e"}}
                       onChange={(e) => this.onChange(e)}
            />
          </MuiThemeProvider>
          <MuiThemeProvider>
            <TextField name="password"
                       value={password}
                       type="password"
                       errorText={errors.password}
                       floatingLabelText="Password"
                       style={inputStyle}
                       floatingLabelStyle={{color: "#252f3e"}}
                       onChange={(e) => this.onChange(e)}
            />
          </MuiThemeProvider>
          <MuiThemeProvider>
            <RaisedButton label="Checkout"
                          labelPosition="before"
                          backgroundColor="#ff6a00"
                          labelColor="white"
                          style={{margin: "40px 0 10px 0"}}
                          onTouchTap={(e) => this.onSubmit(e)}
                          disabled={isLoading}
            />
          </MuiThemeProvider>
        </form>
      </Col>
      <Col md={4}>
        <section styleName="signin-section">
          <MuiThemeProvider>
            <List>
              <Subheader style={{color: "blue", fontSize: "20px"}}>Your Shopping's Cart</Subheader>
              <Row>
                <Col md={8}>
                  <Subheader style={{color: "black", fontSize: "20px"}}>Product</Subheader>
                </Col>
                <Col md={1}>
                  <Subheader style={{color: "black", fontSize: "20px"}}>Quantity</Subheader>
                </Col>
              </Row>
              {
                this.props.cart.map(product => {
                  let { productName, productPrice, productUrl } = product

                  return (
                    <Row>
                      <Col md={8}>
                        <ListItem
                          leftAvatar={<Avatar src={productUrl}/>}
                          primaryText={productName}
                          secondaryText={<p>${productPrice}</p>}
                        />
                      </Col>
                      <Col md={1}>
                        <select
                          name={productPrice}
                          onChange={(e) => this.handlerChangeQuantity(e)}
                          style={{marginTop: "20px", marginLeft: "20px"}}
                        >
                          <option value="1" selected={true}>1</option>
                          <option value="2">2</option>
                          <option value="3">3</option>
                          <option value="4">4</option>
                          <option value="4">5</option>
                        </select>
                      </Col>
                    </Row>
                  )
                })
              }
              <Divider />
              <Subheader style={{color: "black", fontSize: "20px"}}>
                Total Price: <span style={{color: "#ff6a00", fontWeight: "bolder"}}>${this.state.totalPrice}</span>
              </Subheader>
            </List>
          </MuiThemeProvider>
        </section>
      </Col>
    </Row>
    )
  }
}

CheckoutForm.propTypes = {
  cart: React.PropTypes.array.isRequired
}
