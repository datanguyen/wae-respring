import React from 'react'
import { FormGroup, HelpBlock, ControlLabel } from 'react-bootstrap'

export const TextFieldGroup = ({ name, value, label, error, type, onChange, placeholder }) => {
  return (
    <FormGroup validationState={error ? 'error' : null }>
      { label && <ControlLabel>{label}</ControlLabel> }
      <input name={name}
             value={value}
             type={type}
             placeholder={placeholder}
             onChange={onChange}
             className='form-control'
      />
      { error && <HelpBlock>{error}</HelpBlock> }
    </FormGroup>
  )
}

TextFieldGroup.propTypes = {
  name: React.PropTypes.string.isRequired,
  value: React.PropTypes.string.isRequired,
  label: React.PropTypes.string,
  type: React.PropTypes.string.isRequired,
  error: React.PropTypes.string,
  onChange: React.PropTypes.func,
  placeholder: React.PropTypes.string
}
TextFieldGroup.defaultProps = {
  type: 'text',
  label: '',
  error: '',
  onChange: () => {},
  placeholder: ''
}
